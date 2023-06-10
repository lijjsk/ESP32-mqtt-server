package com.Li.esp32mqttserver.service.Impl;

import com.Li.esp32mqttserver.dao.IFacilityDao;
import com.Li.esp32mqttserver.dao.IGoodsDao;
import com.Li.esp32mqttserver.domain.Facility;
import com.Li.esp32mqttserver.domain.Good;
import com.Li.esp32mqttserver.domain.MqttMsg;
import com.Li.esp32mqttserver.mqtt.MyMqttClient;
import com.Li.esp32mqttserver.response.JsonResult;
import com.Li.esp32mqttserver.response.ResultTool;
import com.Li.esp32mqttserver.service.GoodsService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private IGoodsDao iGoodsDao;

    @Autowired
    private IFacilityDao iFacilityDao;
    @Autowired
    private MyMqttClient myMqttClient;
    @Override
    public JsonResult getAllGoods() {
        return ResultTool.success(iGoodsDao.findAll());
    }

    @Override
    @Transactional
    public JsonResult deleteGoods(Long id) {
        iGoodsDao.deleteGoodsByGid(id);
        return ResultTool.success();
    }


    @Override
    @Transactional
    public JsonResult updateGoods(Good good) {

        iGoodsDao.save(good);
        if(iFacilityDao.findFacilityBygId(good.getGid()) != null){
            Facility facility = iFacilityDao.findFacilityBygId(good.getGid());
            MqttMsg mqttMsg = new MqttMsg();
            mqttMsg.setTopicName(facility.getName());
            System.out.println("{\"name\":\""+good.getName()+"\",\"price\":\""+good.getPrice().toString()+"\",\"stock\":\""+good.getStock().toString()+"\"}");
            mqttMsg.setContent("{\"name\":\""+good.getName()+"\",\"price\":\""+good.getPrice().toString()+"\",\"stock\":\""+good.getStock().toString()+"\"}");
            String result = "给主题："+mqttMsg.getTopicName()+"发送成功";
            //发送消息
            myMqttClient.publish(mqttMsg.getContent(),mqttMsg.getTopicName(),mqttMsg.getQos());
            return ResultTool.success(result);
        }else{
            return ResultTool.success(good);
        }
        //return ResultTool.success(iGoodsDao.save(good));
    }
    @Override
    public JsonResult getGoodsByName(String name){
        return ResultTool.success(iGoodsDao.findGoodsByName(name));
    }

//    @Override
//    public Page<Good> listAll(Integer pageNum){
//        Pageable pageable = PageRequest.of(pageNum - 1, 10);
//        Page<Good> all = iGoodsDao.findAll(pageable);
//        return all;
//    }


}
