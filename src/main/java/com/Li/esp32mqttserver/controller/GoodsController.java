package com.Li.esp32mqttserver.controller;

import com.Li.esp32mqttserver.domain.Good;
import com.Li.esp32mqttserver.response.JsonResult;
import com.Li.esp32mqttserver.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping(value = "/search")
    public JsonResult getGoods(@RequestParam String name){
        return goodsService.getGoodsByName(name);
    }

    @DeleteMapping (value = "/delete")
    public JsonResult deleteGoods(@RequestParam Long id){
        return goodsService.deleteGoods(id);
    }

    @PutMapping(value = "/update")
    public JsonResult updateGoods(@RequestBody Good good){
        return goodsService.updateGoods(good);
    }
//    @GetMapping(value = "/getPage")
//    public Page<Good> getPage(Integer pageNum){
//        return goodsService.listAll(pageNum);
//    }
    @GetMapping(value = "/getAllGoods")
    public JsonResult getAllGoods(){
        return goodsService.getAllGoods();
    }
}
