package com.Li.esp32mqttserver.service;

import com.Li.esp32mqttserver.Response.ResponseResult;
import com.Li.esp32mqttserver.dao.UserDao;
import com.Li.esp32mqttserver.domain.LoginUser;
import com.Li.esp32mqttserver.domain.User;
import com.Li.esp32mqttserver.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private AuthenticationManager authenticationManager;
    //用户注册
    public ResponseResult registerStudent(User user){
        try{
            //获取密码编码器
            PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            //将用户的密码进行编码
            String password = passwordEncoder.encode(user.getPass());
            //将编码后的密码覆盖到用户信息中
            user.setPass(password.substring(8));
            //将用户信息持久化到数据库中
            userDao.save(user);
        }catch(Exception e){
            return new ResponseResult(200,"注册失败");
        }
        return new ResponseResult(200,"注册成功");
    }
    //用户登录
    public ResponseResult login(User user){
        //通过UsernamePasswordAuthenticationToken获取用户名和密码
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(user.getName(), user.getPass());
        //AuthenticationManager委托机制对authenticationToken 进行用户认证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证没有通过，给出对应的提示
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }
        //如果认证通过，使用user生成jwt  jwt存入ResponseResult 返回

        //如果认证通过，拿到这个当前登录用户信息
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();

        //获取当前用户的userid
        String userid = loginUser.getUser().getId().toString();

        String jwt = JwtUtil.createJWT(userid);
        Map<String, String> map = new HashMap<>();
        map.put("token",jwt);

        return new ResponseResult(200,"登录成功",map);
    }
    //用户注销
    public ResponseResult logout(){
        //从SecurityContextHolder中的userid
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        //在数据库中删除操作
        userDao.deleteById(userid);
        return new ResponseResult(200,"注销成功");
    }
}
