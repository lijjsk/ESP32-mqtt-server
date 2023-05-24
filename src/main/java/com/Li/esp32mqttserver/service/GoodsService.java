package com.Li.esp32mqttserver.service;

import com.Li.esp32mqttserver.domain.Good;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GoodsService {
    Good addGoods(Good goods);

    List<Good> getAllGoods();

    boolean deleteGoods(Long id);

    Good selectGoods(Long id);

    Good updateGoods(Good goods);

    Page<Good> listAll(Integer pageNum);

}
