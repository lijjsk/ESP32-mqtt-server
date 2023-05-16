package com.example.esp32mqttserver.config;

import com.example.esp32mqttserver.mqtt.MyMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfiguration {
    private static final Logger log = LoggerFactory.getLogger(MqttConfiguration.class);
    @Value("${mqtt.host}")
    String host;
    @Value("${mqtt.username}")
    String username;
    @Value("${mqtt.password}")
    String password;
    @Value("${mqtt.clientId}")
    String clientId;
    @Value("${mqtt.timeout}")
    int timeOut;
    @Value("${mqtt.keepalive}")
    int keepAlive;

    @Bean
    public MyMqttClient myMqttClientBean(){
        MyMqttClient myMqttClient = new MyMqttClient(host,username,password,clientId,timeOut,keepAlive);
        for (int i = 0;i < 10;i++){
            try{
                myMqttClient.connect();
                return myMqttClient;
            }catch (MqttException e){
                log.error("MQTT connect exception,connect time = "+i);
                try{
                    Thread.sleep(2000);
                }catch(InterruptedException e1){
                    e1.printStackTrace();
                }
            }
        }
        return myMqttClient;
    }
}
