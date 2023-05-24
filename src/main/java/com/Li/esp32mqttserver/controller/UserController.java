package com.Li.esp32mqttserver.controller;

import com.Li.esp32mqttserver.domain.LoginUser;
import com.Li.esp32mqttserver.response.ResponseResult;
import com.Li.esp32mqttserver.domain.User;
import com.Li.esp32mqttserver.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
//声明该类为控制类
@RestController
//声明该类为swagger类
@Api(tags = "用户管理相关接口")
@RequestMapping(value = "")
public class UserController {
    @Autowired
    private UserService userService;
    //用户注册
    @RequestMapping("/register")
    public ResponseResult registerStudent(@RequestBody User user){
        return userService.registerStudent(user);
    }
    //用户登出
    @RequestMapping("/mylogout")
    public ResponseResult logout(){
        return userService.logout();
    }
    //测试用
    @RequestMapping("/success")
    public String success(){
        return "success";
    }
    @RequestMapping("/fail")
    public String fail(){
        return "fail";
    }
    //以下三个为首页
    @RequestMapping("/mylogin")
    public String mylogin(){
        return "index";
    }
    @RequestMapping("/")
    public String mylogin2(){
        return "index";
    }
    @RequestMapping("/index")
    public String mylogin3(){
        return "index";
    }
    //登录信息获取表单
    @RequestMapping("/loginForm")
    public ResponseResult loginForm(@RequestBody User user){
        return userService.login(user);
    }
    //展示个人信息接口
    @RequestMapping("/myInfo2")
    public LoginUser getUserInfo2(@RequestBody Authentication authentication){
        return (LoginUser) authentication.getPrincipal();
    }
    //未完善版本
    @RequestMapping("/myInfo")
    public User getUserInfo(@RequestParam Long userid){
        return userService.getUserInfo(userid);
    }
}
