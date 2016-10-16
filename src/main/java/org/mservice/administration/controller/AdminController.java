package org.mservice.administration.controller;

import org.mservice.administration.dao.User;
import org.mservice.administration.service.AdministrationService;
import org.mservice.user.domain.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by oscarimac122 on 16/10/16.
 */
@Controller
public class AdminController {
    private static final Logger logger = Logger.getLogger(AdminController.class.getName());

    @Autowired
    private AdministrationService service;

    @RequestMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        service.deleteUser(id);
        logger.info("getting all users after delete");
        List<User> users = service.getAll();
        if (users!=null && !users.isEmpty())
            model.addAttribute("users", users);
        return "list-users";
    }

    @RequestMapping("/users")
    public String home() {
        logger.info("Go Home");
        return "index";
    }

    @RequestMapping("/users/add")
    public String addUser(Model model) {
        logger.info("returning add page");
        model.addAttribute("user", new User());
        return "user-add";
    }

    @RequestMapping(value="/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, Model model) {
        logger.info("adding user");
        service.add(user);
        logger.info("getting all users");
        List<User> users = service.getAll();
        model.addAttribute("users", users);
        return "list-users";
    }

    @RequestMapping("/users/search")
    public String searchUsers(Model model) {
        logger.info("returning search page");
        model.addAttribute("search", new Search());
        return "user-search";
    }

    @RequestMapping(value="/users/search", method = RequestMethod.POST)
    public String searchUsers(@ModelAttribute("user") Search search, Model model) {
        logger.info("searching users");
        List<User> users = service.search(search);
        model.addAttribute("search", new Search());
        model.addAttribute("users", users);
        return "user-search";
    }

    @RequestMapping("/users/update/{id}")
    public String goUpdatePage(Model model, @PathVariable("id") Long id) {
        logger.info("retrieving user with id: " + id);
        User user = service.getById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    @RequestMapping(value="/users/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("user") User user, Model model) {
        logger.info("updating user");
        service.update(user);
        logger.info("getting all users after update");
        List<User> users = service.getAll();
        model.addAttribute("users", users);
        return "list-users";
    }

    @RequestMapping("/users/all")
    public String getAll(Model model) {
        logger.info("Retrieving all users");
        List<User> users = service.getAll();
        logger.info("here");
        model.addAttribute("users", users);
        return "list-users";
    }

    @RequestMapping("/users/{id}")
    public String getById(Model model, @PathVariable("id") Long id) {
        logger.info("Retrieving user with id: " + id);
        User user = service.getById(id);
        model.addAttribute("user", user);
        return "user-detail";
    }
}
