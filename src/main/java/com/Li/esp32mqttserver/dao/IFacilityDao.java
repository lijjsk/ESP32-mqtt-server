package com.Li.esp32mqttserver.dao;

import com.Li.esp32mqttserver.domain.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFacilityDao extends JpaRepository<Facility,Long>{
    Facility findFacilityByClientId(Long id);

    boolean deleteFacilityByClientId(Long id);
}
