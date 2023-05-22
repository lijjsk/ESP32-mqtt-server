package com.Li.esp32mqttserver.service.Impl;

import com.Li.esp32mqttserver.dao.IAdminDao;
import com.Li.esp32mqttserver.domain.admin;
import com.Li.esp32mqttserver.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private IAdminDao iAdminDao;
    @Override
    public admin getAdminByNameAndPass(String name, String pass) {
        return iAdminDao.findadminBynameAndpass(name,pass);
    }
}
