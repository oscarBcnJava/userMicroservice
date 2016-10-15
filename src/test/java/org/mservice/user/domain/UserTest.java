package org.mservice.user.domain;

import org.junit.Assert;

/**
 * Created by oscarimac122 on 15/10/16.
 */
public class UserTest {
    private User user;
    private static final String PLAYER_NAME_1 = "PLAYER1";
    private static final String PLAYER_PASSWORD_1 = "PASSWORD1";
    private static final String PLAYER_NAME_2 = "PLAYER2";
    private static final String PLAYER_PASSWORD_2 = "PASSWORD2";

    @org.junit.Before
    public void setUp() throws Exception {
        user = new User();
        user.setName(PLAYER_NAME_1);
        user.setPassword(PLAYER_PASSWORD_1);
    }

    @org.junit.Test
    public void checkDataUser() {
        Assert.assertEquals(user.getName(), PLAYER_NAME_1);
        Assert.assertEquals(user.getPassword(), PLAYER_PASSWORD_1);
    }

    public void updateDataUser() {
        user.setName(PLAYER_NAME_2);
        user.setPassword(PLAYER_PASSWORD_2);
        Assert.assertEquals(user.getName(), PLAYER_NAME_2);
        Assert.assertEquals(user.getPassword(), PLAYER_PASSWORD_2);
    }

}