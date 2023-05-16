package com.example.esp32mqttserver.dao;

import com.example.esp32mqttserver.entity.good;
import org.springframework.data.jpa.repository.JpaRepository;

public interface goodDao extends JpaRepository<good,String> {

}
