package org.mservice.server.service.user;

import org.mservice.user.UserConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import java.util.logging.Logger;

/**
 * Created by oscarimac122 on 15/10/16.
 */
@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(UserConfiguration.class)
public class UsersServer {

    private Logger logger = Logger.getLogger(UserConfiguration.class.getName());

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "users-server");
        SpringApplication.run(UsersServer.class, args);
    }
}
