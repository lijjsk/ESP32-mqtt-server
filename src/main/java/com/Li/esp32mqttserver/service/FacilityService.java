package com.Li.esp32mqttserver.service;

import com.Li.esp32mqttserver.dao.FacilityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacilityService {
    @Autowired
    private FacilityDao facilityDao;
}
