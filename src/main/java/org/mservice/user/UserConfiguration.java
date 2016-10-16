package org.mservice.user;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by oscarimac122 on 15/10/16.
 */
@Configuration
@ComponentScan
@EntityScan("org.mservice.user")
@EnableJpaRepositories("org.mservice.user.dao")
@PropertySource("classpath:db/db-config.properties")
public class UserConfiguration {

    private Logger logger = Logger.getLogger(UserConfiguration.class.getName());

    @Bean
    public DataSource dataSource() {
        logger.info("dataSource dummy");
        DataSource dataSource = (new EmbeddedDatabaseBuilder()).addScript("classpath:db/schema.sql")
                .addScript("classpath:db/data.sql").build();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> userList = jdbcTemplate.queryForList("SELECT id from USER ");
        logger.info("there are " + userList.size() + ", in database");

        return dataSource;
    }
}
