package com.example.esp32mqttserver.entity;

import com.example.esp32mqttserver.mqtt.MyMqttClient;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyBusiness {

    public static String rsu = "rsu_state";
    String suffix = "51";

    @Autowired
    private MyMqttClient myMqttClient;

    @PostConstruct
    public void init() {
        log.info("开始初始化了");

        myMqttClient.subscribe(rsu + suffix, 0);
    }

    @Scheduled(cron = "0/50 * * * * ?")
    public void rsu() {
        String s1 = "{\n" +
                "  \"html\": \"车道天线正常\",\n" +
                "  \"msgtype\": \"devicestate\",\n" +
                "  \"name\": \"rsu\",\n" +
                "  \"state\": 0\n" +
                "}";
        myMqttClient.publish(s1, rsu + suffix, 0);
    }
}
