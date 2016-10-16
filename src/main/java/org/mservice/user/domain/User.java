package org.mservice.user.domain;

import javax.persistence.*;
import java.util.Date;

import static org.bouncycastle.cms.RecipientId.password;

/**
 * Created by oscarimac122 on 15/10/16.
 */
@Entity
@NamedQuery(name = "User.findAllUsers",
        query = "select u from User u")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;
    private String nickname;

    @Temporal(TemporalType.DATE)
    private Date creationAccount;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getCreationAccount() {
        return creationAccount;
    }

    public void setCreationAccount(Date creationAccount) {
        this.creationAccount = creationAccount;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", creationAccount=" + creationAccount +
                '}';
    }
}
