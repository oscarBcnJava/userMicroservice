package org.mservice.administration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

/**
 * Created by oscarimac122 on 15/10/16.
 */
@Controller
public class HomeController {
    private static final Logger logger = Logger.getLogger(HomeController.class.getName());

    @RequestMapping("/")
    public String home() {
        logger.info("root url");
        return "index";
    }
}
