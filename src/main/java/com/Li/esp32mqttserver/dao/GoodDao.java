package com.Li.esp32mqttserver.dao;

import com.Li.esp32mqttserver.domain.Good;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodDao extends JpaRepository<Good,Long> {
}
