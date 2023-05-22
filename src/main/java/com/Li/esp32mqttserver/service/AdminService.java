package com.Li.esp32mqttserver.service;


import com.Li.esp32mqttserver.domain.admin;

public interface AdminService{
    admin getAdminByNameAndPass(String name,String pass);
}