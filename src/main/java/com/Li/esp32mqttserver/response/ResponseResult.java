package com.Li.esp32mqttserver.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import static com.Li.esp32mqttserver.response.ResultCode.*;


@Data
public class ResponseResult {
    /**
     * 消息内容
     */
    private String message;

    /**
     * 响应码：参考`ResultCode`
     */
    private Integer code;

    /**
     * 响应中的数据
     */
    private Object data;

    private Map<String,Object> extra = new HashMap<>();
    public ResponseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public ResponseResult putExtra(String key, Object value) {
        this.extra.put(key, value);
        return this;
    }

    public static ResponseResult error(String message) {
        return new ResponseResult(FAILURE.getCode(), message, null);
    }

    public static ResponseResult error() {
        return new ResponseResult(FAILURE.getCode(), ERROR.getMessage(), null);
    }

    public static ResponseResult error(Integer code, String message) {
        return new ResponseResult(code, message, null);
    }

    public static ResponseResult success() {
        return new ResponseResult(SUCCESS.getCode(), SUCCESS.getMessage(), null);
    }

    public static ResponseResult success(Object data) {
        return new ResponseResult(SUCCESS.getCode(),SUCCESS.getMessage(), data);
    }

    public static ResponseResult success(String message, Object data) {
        return new ResponseResult(SUCCESS.getCode(), message, data);
    }

    public static ResponseResult success(Integer code, String message, Object data) {
        return new ResponseResult(code, message, data);
    }

    public static ResponseResult success(Integer code, String message) {
        return new ResponseResult(code, message,null);
    }

    public ResponseResult(Integer code, String msg, Object data) {
        this.code = code;
        this.message = msg;
        this.data = data;
    }
}
