package com.Li.esp32mqttserver;

import com.Li.esp32mqttserver.dao.IFacilityDao;
import com.Li.esp32mqttserver.dao.IGoodsDao;
import com.Li.esp32mqttserver.domain.Facility;
import com.Li.esp32mqttserver.domain.Good;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Esp32MqttServerApplicationTests {

    @Autowired
    public IGoodsDao iGoodsDao;
    @Autowired
    public IFacilityDao iFacilityDao;
    @Test
    void deleteGoodTest() {
        iGoodsDao.deleteGoodsByGid(6L);
    }
    @Test
    void updateGoodTest() {
        Good good = new Good();
        good.setGid(102L);
        good.setName("面包c");
        good.setPrice(100.0);
        good.setStock(100L);
        good.setGoodsDetails(null);
        iGoodsDao.save(good);
    }
    @Test
    void searchGoodTest() {
        System.out.println(iGoodsDao.findGoodsByName("面包"));
    }
    @Test
    void updateFacilityTest(){
        Facility facility = new Facility();
        facility.setClientId(1L);
        facility.setName("test1");
        facility.setGId(1000L);
        iFacilityDao.save(facility);
    }
    @Test
    void searchFacilityTest(){
        System.out.println(iFacilityDao.findFacilityBygId(1L));
    }
    @Test
    void findAllFacilities(){
        System.out.println(iFacilityDao.findAll());
    }
    @Test
    void mqttSendTest(){
        Good good = new Good();
        good.setName("test");
        good.setPrice(100.0);
        good.setStock(100L);
        System.out.println("{\"name\":\""+good.getName()+"\",\"price\":\""+good.getPrice().toString()+"\",\"stock\":\""+good.getStock().toString()+"\"}");
    }
}
//{
//        "name":"Li",
//        "content":"{\"name\":\"client1\",\"price\":\"10\",\"stock\":\"1234\"}",
//        "topicName":"client1"
//        }

