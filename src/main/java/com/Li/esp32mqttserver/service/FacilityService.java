package com.Li.esp32mqttserver.service;

import com.Li.esp32mqttserver.domain.Facility;

import java.util.List;

public interface FacilityService {
    boolean deleteFacilityById(Long id);

    Facility addFacility(Facility facility);

    Facility getFacilityById(Long id);

    List<Facility> getAllFacilities();

    Facility updateFacility(Facility facility);
}
