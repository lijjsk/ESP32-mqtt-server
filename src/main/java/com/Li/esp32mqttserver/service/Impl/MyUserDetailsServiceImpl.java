package com.Li.esp32mqttserver.service.Impl;

import com.Li.esp32mqttserver.dao.IUserDao;
import com.Li.esp32mqttserver.domain.LoginUser;
import com.Li.esp32mqttserver.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IUserDao IUserDao;
    //实现UserDetailsService接口，重写UserDetails方法，自定义用户的信息从数据中查询
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        //（认证，即校验该用户是否存在）查询用户信息
        User user = IUserDao.findUserByName(name);
        //如果没有查询到用户
        if (Objects.isNull(user)){
            throw new RuntimeException("用户名或者密码错误");
        }
        //TODO (授权，即查询用户具有哪些权限)查询对应的用户信息
        //定义一个权限集合
        //List<String> list = new ArrayList<String>(Arrays.asList("test","admin"));
        //把数据封装成UserDetails返回
        return new LoginUser(user);
    }
}
