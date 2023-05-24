package com.Li.esp32mqttserver.dao;

import com.Li.esp32mqttserver.domain.Good;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGoodsDao extends JpaRepository<Good,String>{

        boolean deleteGoodsByGid(Long gid);

        Good findGoodsByGid(Long gid);


}
