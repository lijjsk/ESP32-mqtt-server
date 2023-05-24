package com.Li.esp32mqttserver.service.Impl;

import com.Li.esp32mqttserver.dao.IFacilityDao;
import com.Li.esp32mqttserver.domain.Facility;
import com.Li.esp32mqttserver.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FacilityServiceImpl implements FacilityService {
    @Autowired
    private IFacilityDao iFacilityDao;
    @Override
    public boolean deleteFacilityById(Long id) {
        return iFacilityDao.deleteFacilityByClientId(id);
    }

    @Override
    public Facility addFacility(Facility facility) {
        return iFacilityDao.save(facility);
    }

    @Override
    public Facility getFacilityById(Long id) {
        return iFacilityDao.findFacilityByClientId(id);
    }

    @Override
    public List<Facility> getAllFacilities() {
        return iFacilityDao.findAll();
    }

    @Override
    public Facility updateFacility(Facility facility) {
        return iFacilityDao.save(facility);
    }
}
