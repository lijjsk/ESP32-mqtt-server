package com.Li.esp32mqttserver.controller;

import com.Li.esp32mqttserver.response.JsonResult;
import com.Li.esp32mqttserver.response.ResultTool;
import com.Li.esp32mqttserver.util.RedisUtil;
import com.Li.esp32mqttserver.domain.MqttMsg;
import com.Li.esp32mqttserver.mqtt.MyMqttClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//用于测试mqtt的控制类
@RestController
@RequestMapping("/mqtt")
public class MqttController {
    /**
     * 客户端
     */
    @Autowired
    private MyMqttClient myMqttClient;

    /**
     * redis
     */
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 创建主题
     * @param topicName
     * @return
     */
    @PostMapping("/createTopic")
    public JsonResult createTopic(String user, String topicName){
        //直接将主题放在缓存中，用的时候从缓存中取出来
        redisUtil.set(user,topicName);
        return ResultTool.success("创建成功，主题为："+topicName);
    }

    /**
     * 根据用户获取主题
     * @param user
     * @return
     */
    @PostMapping("/getTopic")
    public JsonResult getTopic(String user){
        String topicName = redisUtil.get(user).toString();
        return ResultTool.success(topicName);
    }

    /**
     * 订阅主题
     */
    @PostMapping("/subscribeTopic")
    public JsonResult subscribeTopic(String user){
        String topicName = redisUtil.get(user).toString();
        myMqttClient.subscribe(topicName,1);
        return ResultTool.success("订阅"+topicName+"主题成功");
    }

    /**
     * 取消订阅主题
     */
    @PostMapping("/cleanSubscribeTopic")
    public JsonResult cleanSubscribeTopic(String user){
        String topicName = redisUtil.get(user).toString();
        myMqttClient.cleanTopic(topicName);
        return ResultTool.success("取消订阅"+topicName+"主题成功");
    }


    /**
     * 发送消息
     */
    @PostMapping("/sendMsg")
    @ResponseBody
    public  synchronized JsonResult sendMsg(@RequestBody MqttMsg mqttMsg){
        String result = "给主题："+mqttMsg.getTopicName()+"发送成功";
        //发送消息
        myMqttClient.publish(mqttMsg.getContent(),mqttMsg.getTopicName(),mqttMsg.getQos());
        return ResultTool.success(result);
    }
}


