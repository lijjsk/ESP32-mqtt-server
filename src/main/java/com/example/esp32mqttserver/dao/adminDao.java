package com.example.esp32mqttserver.dao;

import com.example.esp32mqttserver.entity.admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface adminDao extends JpaRepository<admin,String> {
}
