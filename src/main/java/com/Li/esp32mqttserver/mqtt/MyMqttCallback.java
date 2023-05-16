package com.Li.esp32mqttserver.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyMqttCallback implements MqttCallbackExtended {
    private static final Logger log = LoggerFactory.getLogger(MyMqttCallback.class);

    private MyMqttClient myMqttClient;
    public MyMqttCallback(MyMqttClient myMqttClient){
        this.myMqttClient = myMqttClient;
    }
    //丢失连接时重连，只会调用一次
    @Override
    public void connectionLost(Throwable throwable){
        log.error("mqtt connection 连接断开，5s后尝试重连：{}",throwable.getMessage());
        long reconnectTimes = 1;
        while(true){
            try{
                if (MyMqttClient.getClient().isConnected()){
                    //判断是否已经重新连接成功 需要重新订阅主题 可以在这个if里面订阅主题 或者 connectComplete（方法里面） 自己选择
                    log.warn("mqtt reconnect success end 重新连接 重新订阅成功");
                    return;
                }
                reconnectTimes+=1;
                log.warn("mqtt reconnect times = {} try again... mqtt 重新订阅时间 {}",reconnectTimes,reconnectTimes);
                MyMqttClient.getClient().reconnect();
            }catch (MqttException e){
                log.error("mqtt断连异常",e);
            }
            try{
                Thread.sleep(5000);
            }catch (InterruptedException e){

            }
        }
    }
    //subscribe后得到的消息会执行到这里面，订阅者收到消息之后执行
    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception{
        System.out.println("服务器发完信息之后调用");
        log.info("接受信息主题 ：{}，接受信息内容：{}",topic,new String(mqttMessage.getPayload()));
    }
    //连接成功之后的回调 可以在这个方法执行 订阅主题 生成Bean的 MqttConfiguration方法中订阅主题 出现bug
    //重新连接后 主题也需要再次订阅 将重新订阅主题放在连接成功后的回调 比较合理
    @Override
    public void connectComplete(boolean reconnect,String serverURI){
        log.info("mqtt 连接成功，连接方式：{}",reconnect?"重连":"直连");
        //订阅主题（可以在这里订阅主题）
    }
    //消息到达后 subscribe后，执行的回调函数 publish后，配送完成后回调的方法
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken){
        System.out.println("接受到已经发布的QoS 1 或 QoS 2 消息的传递令牌时调用");
        log.info("==========deliveryComplete={}===========",iMqttDeliveryToken.isComplete());
    }
}
