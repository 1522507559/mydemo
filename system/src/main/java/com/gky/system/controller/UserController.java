package com.gky.system.controller;

import com.gky.system.entity.User;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zgp
 * @Date 2022/3/14 10:30
 */
@RestController
public class UserController {

    public Object getUserInfo(String username){
        User user = new User(username,"123");

        return user;

    }
}
