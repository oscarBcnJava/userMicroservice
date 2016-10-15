package org.mservice.user.rest;

import org.mservice.user.constants.FormatType;
import org.mservice.user.dao.UserRepository;
import org.mservice.user.date.DateHelper;
import org.mservice.user.domain.User;
import org.mservice.user.exception.search.SearchNotFoundException;
import org.mservice.user.exception.user.UserNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by oscarimac122 on 15/10/16.
 */
@RestController
public class UserMapping {

    private final static Logger logger = Logger.getLogger(UserMapping.class.getName());
    private UserRepository userRepository;

    public UserMapping(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/users/{idPlayer}")
    public User byIdPlayer(@PathVariable("idPlayer") String idPlayer) {
        logger.info("Retrieving user with idplayer: " + idPlayer);
        User user = userRepository.findByIdPlayer(idPlayer);
        if (user == null) {
            throw new UserNotFoundException(idPlayer);
        } else {
            logger.info("User with idPlayer: " + idPlayer + ", found");
        }
        return user;
    }

    @RequestMapping("users/find/{start}")
    public List<User> byDateRegistered(@PathVariable("start") Date startDateRegistration) {
        logger.info("Retrieving all users registered later that: " + startDateRegistration);

        List<User> users = userRepository.findUsersRegisteredAfter(startDateRegistration);

        if (users == null) {
            throw new SearchNotFoundException(DateHelper.getDateAsString(FormatType.DD_MM_YYYY, startDateRegistration));
        } else {
            logger.info("Search returned: " + users.size() + " results");
        }

        return users;
    }
}
