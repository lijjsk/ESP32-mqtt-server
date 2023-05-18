package com.Li.esp32mqttserver.service;

import com.Li.esp32mqttserver.dao.UserDao;
import com.Li.esp32mqttserver.domain.LoginUser;
import com.Li.esp32mqttserver.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    //实现UserDetailsService接口，重写UserDetails方法，自定义用户的信息从数据中查询
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        //（认证，即校验该用户是否存在）查询用户信息
        User user = userDao.findUserByName(name);
        //如果没有查询到用户
        if (Objects.isNull(user)){
            throw new RuntimeException("用户名或者密码错误");
        }
        //把数据封装成UserDetails返回
        return new LoginUser(user);
    }
}
