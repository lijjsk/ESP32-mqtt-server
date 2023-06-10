package com.Li.esp32mqttserver.controller;

import com.Li.esp32mqttserver.domain.Facility;
import com.Li.esp32mqttserver.response.JsonResult;
import com.Li.esp32mqttserver.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Facility")
public class FacilityController {
    @Autowired
    private FacilityService facilityService;

    @GetMapping(value = "/search")
    public JsonResult getFacility(@RequestParam String name){
        return facilityService.getFacilityByName(name);
    }
    @GetMapping(value = "/getAllFacilities")
    public JsonResult getAllFacilities(){
        return facilityService.getAllFacilities();
    }
    @DeleteMapping (value = "/delete")
    public JsonResult deleteGoods(@PathVariable Long id){
        return facilityService.deleteFacilityById(id);
    }

    @PutMapping(value = "/update")
    public JsonResult updateGoods(@RequestBody Facility facility){
        return facilityService.updateFacility(facility);
    }

}
