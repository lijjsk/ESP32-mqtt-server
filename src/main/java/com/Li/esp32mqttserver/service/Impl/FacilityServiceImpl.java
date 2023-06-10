package com.Li.esp32mqttserver.service.Impl;

import com.Li.esp32mqttserver.dao.IFacilityDao;
import com.Li.esp32mqttserver.dao.IGoodsDao;
import com.Li.esp32mqttserver.domain.Facility;
import com.Li.esp32mqttserver.domain.Good;
import com.Li.esp32mqttserver.domain.MqttMsg;
import com.Li.esp32mqttserver.mqtt.MyMqttClient;
import com.Li.esp32mqttserver.response.JsonResult;
import com.Li.esp32mqttserver.response.ResultTool;
import com.Li.esp32mqttserver.service.FacilityService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FacilityServiceImpl implements FacilityService {
    @Autowired
    private IFacilityDao iFacilityDao;
    @Autowired
    private IGoodsDao iGoodsDao;
    @Autowired
    private MyMqttClient myMqttClient;
    @Override
    public JsonResult deleteFacilityById(Long id) {
        return ResultTool.success();
    }

    @Override
    public JsonResult getFacilityByName(String name) {
        return ResultTool.success(iFacilityDao.findFacilitiesByName(name));
    }

    @Override
    public JsonResult getAllFacilities() {
        return ResultTool.success(iFacilityDao.findAll());
    }
    @Override
    public JsonResult updateFacility(Facility facility) {
        iFacilityDao.save(facility);
        if(iGoodsDao.findGoodByGid(facility.getGId()) != null){
            Good good = iGoodsDao.findGoodByGid(facility.getGId());
            MqttMsg mqttMsg = new MqttMsg();
            mqttMsg.setTopicName(facility.getName());
            System.out.println("{\"name\":\""+good.getName()+"\",\"price\":\""+good.getPrice().toString()+"\",\"stock\":\""+good.getStock().toString()+"\"}");
            mqttMsg.setContent("{\"name\":\""+good.getName()+"\",\"price\":\""+good.getPrice().toString()+"\",\"stock\":\""+good.getStock().toString()+"\"}");
            String result = "给主题："+mqttMsg.getTopicName()+"发送成功";
            //发送消息
            myMqttClient.publish(mqttMsg.getContent(),mqttMsg.getTopicName(),mqttMsg.getQos());
            return ResultTool.success(result);
        }else{
            return ResultTool.success(facility);
        }
        //return ResultTool.success(iFacilityDao.save(facility));
    }
}
