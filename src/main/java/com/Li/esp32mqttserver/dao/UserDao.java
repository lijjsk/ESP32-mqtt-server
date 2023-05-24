package com.Li.esp32mqttserver.dao;

import com.Li.esp32mqttserver.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User,Long> {
    //根据用户名查找用户
    User findUserByName(String name);
    User findUserById(Long id);
}
