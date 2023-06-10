package com.Li.esp32mqttserver.dao;

import com.Li.esp32mqttserver.domain.Facility;
import com.Li.esp32mqttserver.domain.Good;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFacilityDao extends JpaRepository<Facility,Long>{
    List<Facility> findFacilitiesByName(String name);
    Facility findFacilityBygId(Long gId);
}
