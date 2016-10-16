package org.mservice.server.service.administration;

import org.mservice.administration.controller.AdminController;
import org.mservice.administration.controller.HomeController;
import org.mservice.administration.service.AdministrationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

/**
 * Created by oscarimac122 on 15/10/16.
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters = false)
public class AdministrationServer {
    private final static Logger logger = Logger.getLogger(AdministrationServer.class.getName());

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "administration-server");
        logger.info("Starting Administration SERVER");
        SpringApplication.run(AdministrationServer.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public AdministrationService administrationService() {
        return new AdministrationService();
    }

    @Bean
    public HomeController homeController() {
        return new HomeController();
    }

    @Bean
    public AdminController controller() {
        return new AdminController();
    }
}
