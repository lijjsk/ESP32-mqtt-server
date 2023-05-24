package com.Li.esp32mqttserver.controller;

import com.Li.esp32mqttserver.domain.LoginUser;
import com.Li.esp32mqttserver.response.JsonResult;
import com.Li.esp32mqttserver.response.ResponseResult;
import com.Li.esp32mqttserver.domain.User;
import com.Li.esp32mqttserver.service.Impl.UserServiceImpl;
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
    private UserServiceImpl userServiceImpl;
    //用户注册
    @RequestMapping("/register")
    public JsonResult registerUser(@RequestBody User user){
        return userServiceImpl.registerUser(user);
    }
    //用户登出
    @RequestMapping("/mylogout")
    public JsonResult logout(){
        return userServiceImpl.logout();
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
    public JsonResult loginForm(@RequestBody User user){
        return userServiceImpl.login(user);
    }
    //展示个人信息接口
    @RequestMapping("/myInfo2")
    public LoginUser getUserInfo2(@RequestBody Authentication authentication){
        return (LoginUser) authentication.getPrincipal();
    }
    //未完善版本
    @RequestMapping("/myInfo")
    public JsonResult getUserInfo(@RequestParam Long userid){
        return userServiceImpl.getUserInfo(userid);
    }
}
