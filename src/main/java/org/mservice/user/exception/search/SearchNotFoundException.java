package org.mservice.user.exception.search;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.logging.Logger;

/**
 * Created by oscarimac122 on 15/10/16.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class SearchNotFoundException extends RuntimeException {

    private Logger logger = Logger.getLogger(SearchNotFoundException.class.getName());

    public SearchNotFoundException(Long startId, Long endId) {
        logger.info("Search didn't return any result for start: " + startId + ", and end: " + endId);
    }
}
