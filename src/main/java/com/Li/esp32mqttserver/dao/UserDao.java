package com.Li.esp32mqttserver.dao;

import com.Li.esp32mqttserver.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {
    User findUserByName(String username);
}
