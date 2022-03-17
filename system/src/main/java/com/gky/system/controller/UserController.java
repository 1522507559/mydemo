package com.gky.system.controller;

import com.gky.system.entity.User;
import common.model.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zgp
 * @Date 2022/3/14 10:30
 */
@RestController
@RequestMapping("/ucenter")
public class UserController extends BaseController {
    @GetMapping("/find")
    public Object getUserInfo(@RequestParam String username){
        User user = new User(username,"123");

        return user;

    }
}
