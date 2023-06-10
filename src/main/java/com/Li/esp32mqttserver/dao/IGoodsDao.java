package com.Li.esp32mqttserver.dao;

import com.Li.esp32mqttserver.domain.Good;
import com.Li.esp32mqttserver.response.JsonResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IGoodsDao extends JpaRepository<Good,Long>{
        @Transactional
        Long deleteGoodsByGid(Long gid);

        List<Good> findGoodsByName(String name);

        Good findGoodByGid(Long id);

}
