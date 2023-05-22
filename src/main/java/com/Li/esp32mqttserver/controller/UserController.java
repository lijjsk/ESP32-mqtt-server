package com.Li.esp32mqttserver.controller;

import com.Li.esp32mqttserver.Response.ResponseResult;
import com.Li.esp32mqttserver.domain.User;
import com.Li.esp32mqttserver.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//声明该类为控制类
@RestController
//声明该类为swagger类
@Api(tags = "用户管理相关接口")
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation("学生注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "student",value = "学生信息JSON数据",defaultValue = "null",required = true)
    })
    @ApiResponse(code = 200,message = "操作成功",response = boolean.class)
    public ResponseResult registerStudent(@RequestBody User user){
        return userService.registerStudent(user);
    }
    @RequestMapping("/login")
    public ResponseResult login(@RequestBody User user){
        return userService.login(user);
    }
    @RequestMapping("/logout")
    public ResponseResult logout(){
        return userService.logout();
    }
    @RequestMapping("/test")
    public String login(){
        return "test";
    }
}
