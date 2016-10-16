package org.mservice.user.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by oscarimac122 on 15/10/16.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException (String nickname) {
        super("User with nickname: " + nickname + ", not exists");
    }

    public UserNotFoundException (Long id) {
        super("User with id: " + id+ ", not exists");
    }
}
