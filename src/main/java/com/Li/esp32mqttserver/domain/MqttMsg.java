package com.Li.esp32mqttserver.domain;

import lombok.Data;

@Data
public class MqttMsg {

    /**
     * 名称
     */
    private String name = "";
    /**
     * 内容
     */
    private String content = "";
    /**
     * 时间
     */
    private String time = "";

    /**
     * 主题
     */
    private String topicName = "";

    /**
     * 发送的qos数字
     * QoS0，At most once，至多一次；
     * QoS1，At least once，至少一次；
     * QoS2，Exactly once，确保只有一次。
     */
    private int qos ;


}