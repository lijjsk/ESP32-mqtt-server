package com.Li.esp32mqttserver.controller;

import com.Li.esp32mqttserver.Response.ResponseResult;
import com.Li.esp32mqttserver.domain.User;
import com.Li.esp32mqttserver.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public ResponseResult login(@RequestBody User user){
        return loginService.login(user);
    }
    @RequestMapping("/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }

    @RequestMapping("/test")
    public String login(){
        return "test";
    }
}
