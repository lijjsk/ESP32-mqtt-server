package com.Li.esp32mqttserver.service;

import com.Li.esp32mqttserver.dao.GoodDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodService {
    @Autowired
    private GoodDao goodDao;
}
