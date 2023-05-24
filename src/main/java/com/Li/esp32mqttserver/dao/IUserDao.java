package com.Li.esp32mqttserver.dao;

import com.Li.esp32mqttserver.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDao extends JpaRepository<User,Long> {
    //根据用户名查找用户
    User findUserByName(String name);
    User findUserById(Long id);
}
