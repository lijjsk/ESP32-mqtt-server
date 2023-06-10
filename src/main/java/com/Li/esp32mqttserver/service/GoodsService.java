package com.Li.esp32mqttserver.service;

import com.Li.esp32mqttserver.domain.Good;
import com.Li.esp32mqttserver.response.JsonResult;
import org.springframework.data.domain.Page;
import springfox.documentation.spring.web.json.Json;

import java.util.List;

public interface GoodsService {

    JsonResult getAllGoods();

    JsonResult deleteGoods(Long id);


    JsonResult updateGoods(Good goods);

    //Page<Good> listAll(Integer pageNum);

    JsonResult getGoodsByName(String name);

}
