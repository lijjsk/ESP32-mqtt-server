package com.Li.esp32mqttserver.service.Impl;

import com.Li.esp32mqttserver.dao.IGoodsDao;
import com.Li.esp32mqttserver.domain.Good;
import com.Li.esp32mqttserver.service.GoodsService;
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


    @Override
    @Transactional
    public Good addGoods(Good goods) {
        return iGoodsDao.save(goods) ;
    }

    @Override
    public List<Good> getAllGoods() {
        return iGoodsDao.findAll();
    }

    @Override
    @Transactional
    public boolean deleteGoods(Long id) {
        return iGoodsDao.deleteGoodsByGid(id);
    }

    @Override
    public Good selectGoods(Long id) {
        return iGoodsDao.findGoodsByGid(id);
    }

    @Override
    @Transactional
    public Good updateGoods(Good goods) {
        return iGoodsDao.save(goods);
    }

    @Override
    public Page<Good> listAll(Integer pageNum){

        Pageable pageable = PageRequest.of(pageNum - 1, 10);
        Page<Good> all = iGoodsDao.findAll(pageable);
        return all;
    }



}
