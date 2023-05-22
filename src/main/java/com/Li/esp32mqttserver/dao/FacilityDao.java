package com.Li.esp32mqttserver.dao;

import com.Li.esp32mqttserver.domain.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityDao extends JpaRepository<Facility,Long> {
}
