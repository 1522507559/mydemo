package com.gky.system.entity;

import lombok.Data;

/**
 * @Author: zgp
 * @Date 2022/3/15 16:06
 */
@Data
public class User {
    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
