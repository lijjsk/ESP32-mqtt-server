package com.Li.esp32mqttserver.dao;

import com.Li.esp32mqttserver.domain.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFacilityDao extends JpaRepository<Facility,Long>{
    Facility findfacilityByClientId(Long id);

    boolean deletefacilityByClientId(Long id);
}
