package com.gky.auth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "system-service")
public interface UserClient {
    //根据账号查询用户信息
    @GetMapping("sys/ucenter/find")
    public  Object getUserInfo(@RequestParam("username") String username);
}
