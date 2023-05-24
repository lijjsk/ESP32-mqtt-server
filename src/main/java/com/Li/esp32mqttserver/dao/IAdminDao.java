package com.Li.esp32mqttserver.dao;

import com.Li.esp32mqttserver.domain.admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminDao extends JpaRepository<admin,Long>{
    admin findadminBynameAndpass(String name,String pass);
}