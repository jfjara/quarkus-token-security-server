package com.jfjara.infraestructure.mongodb.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;

@MongoEntity(collection="user")
public class UserEntity extends PanacheMongoEntity {

    public String username;
    public String password;

    public static UserEntity getUser(final String username, final String password) {
        return find("username = ?1 and password = ?2", username, password).firstResult();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
