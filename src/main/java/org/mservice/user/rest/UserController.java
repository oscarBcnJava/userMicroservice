package org.mservice.user.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mservice.user.dao.UserRepository;
import org.mservice.user.domain.User;
import org.mservice.user.exception.search.SearchNotFoundException;
import org.mservice.user.exception.user.UserNotFoundException;
import org.mservice.user.validator.UserValidator;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by oscarimac122 on 15/10/16.
 */
@RestController
public class UserController {
    private final static Logger logger = Logger.getLogger(UserController.class.getName());
    private final ObjectMapper objectMapper;
    private UserRepository userRepository;
    private UserValidator userValidator;

    @Autowired
    public UserController(UserRepository userRepository, ObjectMapper objectMapper, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
        this.userValidator = userValidator;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable Long id) {
        logger.info("Retrieving user with id: " + id);
        User user = (User)userRepository.findOne(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        } else {
            logger.info("User with id: " + id + ", found");
        }
        return user;
    }

    @RequestMapping("/search")
    public List<User> betweenIds(@RequestParam(value="startId", required=false) Long startId, @RequestParam(value="endId", required = false) Long endId) {
        logger.info("Retrieving all users registered betweeen id: " + startId + ", and id: " + endId );

        List<User> users = userRepository.findUsersBetweenIds(startId, endId);

        if (users == null) {
            throw new SearchNotFoundException(startId, endId);
        } else {
            logger.info("Search returned: " + users.size() + " results");
        }

        return users;
    }

    @RequestMapping(value="/all", method = RequestMethod.GET)
    public List<User> findAll() {
        logger.info("Looking for all users");
        return userRepository.findAllUsers();
    }

    @RequestMapping(method = RequestMethod.POST)
    public User create(@RequestBody User user) {
        return (User)userRepository.save(user);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public HttpEntity update() throws IOException {
        logger.info("testing");
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public HttpEntity update(@PathVariable Long id, @RequestBody User updatedUser) throws IOException {
        User actualUser = findById(id);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("nickname", updatedUser.getNickname());
        propertyValues.add("name", updatedUser.getName());
        propertyValues.add("password", updatedUser.getPassword());
        DataBinder binder = new DataBinder(updatedUser);
        binder.addValidators(userValidator);
        binder.bind(propertyValues);
        binder.validate();
        if (binder.getBindingResult().hasErrors()) {
            return new ResponseEntity<>(binder.getBindingResult().getAllErrors(), HttpStatus.BAD_REQUEST);
        } else {
            actualUser.setNickname(updatedUser.getNickname());
            actualUser.setName(updatedUser.getName());
            actualUser.setPassword(updatedUser.getPassword());
            //actualUser.setCreationAccount(updatedUser.getCreationAccount());
            userRepository.save(actualUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public HttpEntity add(@RequestBody User user, UriComponentsBuilder ucBuilder) throws IOException {
        DataBinder binder = new DataBinder(user);
        Date today = Calendar.getInstance().getTime();
        user.setCreationAccount(today);
        userRepository.save(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public HttpEntity delete(@PathVariable Long id) {
        User user = findById(id);
        userRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
