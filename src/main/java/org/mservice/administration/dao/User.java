package org.mservice.administration.dao;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Date;

/**
 * Created by oscarimac122 on 15/10/16.
 */
@JsonRootName("User")
public class User {
    private Long id;
    private String name;
    private String password;
    private Date creationAccount;
    private String nickname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreationAccount() {
        return creationAccount;
    }

    public void setCreationAccount(Date creationAccount) {
        this.creationAccount = creationAccount;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
