package com.gky.auth.service;

import com.gky.auth.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    private UserClient userClient;

    public Object getUserInfo(){
        //Object userInfo = userClient.getUserInfo("zhangsan");
        return "zhangsan";
    }
}
