package org.mservice.administration.service;

import org.mservice.administration.dao.User;
import org.mservice.user.domain.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by oscarimac122 on 15/10/16.
 */
@Service
public class AdministrationService {
    private static final Logger logger = Logger.getLogger(AdministrationService.class.getName());
    private String USERS_SERVICE_URL = "http://USERS-SERVER";

    @Autowired
    private RestTemplate restTemplate;

    public List<User> getUsersById(Long startId, Long endId) {
        return null;
    }

    public List<User> search(Search upDown) {
        logger.info("Search  Users: ");
        User[] users = null;
        try {
            URI targetUrl= UriComponentsBuilder.fromUriString(USERS_SERVICE_URL)
                    .path("/search")
                    .queryParam("startId", upDown.getStartId())
                    .queryParam("endId", upDown.getEndId())
                    .build()
                    .toUri();
            users = restTemplate.getForObject(targetUrl, User[].class);
        } catch (HttpClientErrorException e) {
            logger.log(Level.SEVERE, "Error searching users");
        }
        if (users != null) {
            return Arrays.asList(users);
        }
        return Collections.EMPTY_LIST;
    }

    public void update(User user) {
        logger.info("update user");
        String uri = USERS_SERVICE_URL + "/{id}";
        Map<String, Long> params = new HashMap<>();
        params.put("id", user.getId());
        try {
            restTemplate.put(uri, user, params);
        } catch (HttpClientErrorException e) {
            logger.log(Level.SEVERE, "Error getting all users");
        }
    }

    public void add(User user) {
        logger.info("update user");
        String uri = USERS_SERVICE_URL + "/add";
        try {
            restTemplate.postForObject(uri, user, User.class);
        } catch (HttpClientErrorException e) {
            logger.log(Level.SEVERE, "Error adding new user");
        }
    }


    public List<User> getAll() {
        logger.info("Getting all Users: ");
        User[] users = null;
        try {
            users = restTemplate.getForObject(USERS_SERVICE_URL
                    + "/all", User[].class);
        } catch (HttpClientErrorException e) {
            logger.log(Level.SEVERE, "Error getting all users");
        }
        if (users != null) {
            return Arrays.asList(users);
        }
        return Collections.EMPTY_LIST;
    }

    public User getById(Long id) {
        logger.info("Getting User by id: " + id);
        return restTemplate.getForObject(USERS_SERVICE_URL + "/{id}",
                User.class, id);
    }

    public void deleteUser(Long id) {
        logger.info("Deleting user");
        restTemplate.delete(USERS_SERVICE_URL + "/{id}", id);
    }
}
