package com.Li.esp32mqttserver.controller;

import com.Li.esp32mqttserver.domain.Good;
import com.Li.esp32mqttserver.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping(value = "/{gid}")
    public Good getGoods(@PathVariable Long gid){
        return goodsService.selectGoods(gid);
    }

    @PostMapping(value = "/add")
    public Good addGoods(@RequestBody Good goods){
        Good goods1 = goodsService.addGoods(goods);
        return goods1;
    }

    @DeleteMapping (value = "/delete/{id}")
    public boolean deleteGoods(@PathVariable Long id){
        return goodsService.deleteGoods(id);
    }

    @PutMapping(value = "/update")
    public boolean updateGoods(@RequestBody Good goods){
        Good goods1 = goodsService.updateGoods(goods);
        return goods1 != null;
    }
}
