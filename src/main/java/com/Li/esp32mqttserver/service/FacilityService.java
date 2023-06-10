package com.Li.esp32mqttserver.service;

import com.Li.esp32mqttserver.domain.Facility;
import com.Li.esp32mqttserver.response.JsonResult;

import java.util.List;

public interface FacilityService {
    JsonResult deleteFacilityById(Long id);

    JsonResult updateFacility(Facility facility);

    JsonResult getFacilityByName(String name);

    JsonResult getAllFacilities();

}
