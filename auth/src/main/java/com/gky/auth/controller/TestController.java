package com.gky.auth.controller;

import com.gky.auth.service.TestService;
import common.model.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController extends BaseController {
    @Autowired
    TestService testService;

    @GetMapping("/user")
    public Object getUser(){
        Object userInfo = testService.getUserInfo();
        return renderSuccess(userInfo);
    }
}
