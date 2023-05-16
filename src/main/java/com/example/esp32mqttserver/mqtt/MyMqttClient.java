package com.example.esp32mqttserver.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

@Slf4j
public class MyMqttClient {
    private static MqttClient client;
    private String host;
    private String username;
    private String password;
    private String clientID;

    public void setHost(String host) {
        this.host = host;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setKeepalive(int keepalive) {
        this.keepalive = keepalive;
    }

    private int timeout;
    private int keepalive;

    public MyMqttClient(String host, String username, String password, String clientID, int timeout, int keepalive) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.clientID = clientID;
        this.timeout = timeout;
        this.keepalive = keepalive;
    }

    public static void setClient(MqttClient client){
        MyMqttClient.client = client;
    }

    public static MqttClient getClient() {
        return client;
    }

    //设置mqtt连接参数
    public MqttConnectOptions setMqttConnectOptions(String username,String password,int timeout,int keepalive){
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        options.setConnectionTimeout(timeout);
        options.setKeepAliveInterval(keepalive);
        options.setCleanSession(true);
        options.setAutomaticReconnect(true);
        return options;
    }
    //连接mqtt服务段，获得MqttClient服务对象
    public void connect() throws MqttException{
        if(client == null){
            client = new MqttClient(host,clientID,new MemoryPersistence());
            client.setCallback(new MyMqttCallback(MyMqttClient.this));
        }
        MqttConnectOptions mqttConnectOptions = setMqttConnectOptions(username,password,timeout,keepalive);
        if (!client.isConnected()){
            client.connect(mqttConnectOptions);
        }else {
            client.disconnect();
            client.connect(mqttConnectOptions);
        }
        //未发生异常，则连接成功
        log.info("mqtt connect success");
    }
    //发布 默认qos为0 非持久化
    public void publish(String pushMessage,String topic,int qos){
        publish(pushMessage,topic,qos,false);
    }
    //发布消息
    public void publish(String pushMessage,String topic,int qos,boolean retained){
        MqttMessage message = new MqttMessage();
        message.setPayload(pushMessage.getBytes());
        message.setQos(qos);
        message.setRetained(retained);
        MqttTopic mqttTopic = MyMqttClient.getClient().getTopic(topic);
        if (null == mqttTopic){
            log.error("主题没有找到");
        }
        //Delivery:配送
        MqttDeliveryToken token;
        //这里必须同步否则，在多线程publish的情况下，线程会发生死锁
        synchronized (this){
            try{
                //也是发送到执行队列中，等待执行线程执行，将消息发送到消息中间件
                token = mqttTopic.publish(message);
                token.waitForCompletion(1000L);
            }catch (MqttException e){
                e.printStackTrace();
            }
        }
    }
    //订阅某个主题
    public void subscribe(String topic,int qos){
        try{
            MyMqttClient.getClient().subscribe(topic,qos);
            log.info("订阅主题"+topic+"成功");
        }catch (MqttException e){
            e.printStackTrace();
        }
    }
    //取消订阅某个主题
    public void cleanTopic(String topic){
        if(client != null && client.isConnected()){
            try{
                client.unsubscribe(topic);
            }catch (MqttException e){
                e.printStackTrace();
            }
        }else{
            log.info("取消订阅主题失败!");
        }
    }
}
