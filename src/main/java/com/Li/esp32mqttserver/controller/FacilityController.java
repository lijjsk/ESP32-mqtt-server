package com.Li.esp32mqttserver.controller;

import com.Li.esp32mqttserver.domain.Facility;
import com.Li.esp32mqttserver.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Facility")
public class FacilityController {
    @Autowired
    private FacilityService facilityService;

    @GetMapping(value = "/{ClientId}")
    public Facility getFacility(@PathVariable Long ClientId){
        return facilityService.getFacilityById(ClientId);
    }

    @PostMapping(value = "/add")
    public boolean addFacility(@RequestBody Facility facility){
        Facility facility1 = facilityService.addFacility(facility);
        return facility1 != null;
    }

    @DeleteMapping (value = "/delete/{id}")
    public boolean deleteGoods(@PathVariable Long id){
        return facilityService.deleteFacilityById(id);
    }

    @PutMapping(value = "/update")
    public boolean updateGoods(@RequestBody Facility facility){
        Facility facility1 = facilityService.updateFacility(facility);
        return facility1 != null;
    }
}
