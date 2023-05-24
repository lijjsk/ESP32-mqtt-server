package com.Li.esp32mqttserver.response;

import lombok.Data;


import java.io.Serializable;
//统一JSON返回结果

@Data
public class JsonResult<T> implements Serializable {
    private Boolean success;
    private Integer code;
    private String message;
    private T data;
    public JsonResult() {
    }

    public JsonResult(boolean success) {
        this.success = success;
        this.code = success ? ResultEnum.SUCCESS.getCode() : ResultEnum.FAILURE.getCode();
        this.message = success ? ResultEnum.SUCCESS.getMessage() : ResultEnum.FAILURE.getMessage();
    }

    public JsonResult(boolean success, ResultEnum resultEnum) {
        this.success = success;
        this.code = success ? ResultEnum.SUCCESS.getCode() : (resultEnum == null ? ResultEnum.FAILURE.getCode() : resultEnum.getCode());
        this.message = success ? ResultEnum.SUCCESS.getMessage() : (resultEnum == null ? ResultEnum.FAILURE.getMessage() : resultEnum.getMessage());
    }

    public JsonResult(boolean success, T data) {
        this.success = success;
        this.code = success ? ResultEnum.SUCCESS.getCode() : ResultEnum.FAILURE.getCode();
        this.message = success ? ResultEnum.SUCCESS.getMessage() : ResultEnum.FAILURE.getMessage();
        this.data = data;
    }

    public JsonResult(boolean success, ResultEnum resultEnum, T data) {
        this.success = success;
        this.code = success ? ResultEnum.SUCCESS.getCode() : (resultEnum == null ? ResultEnum.FAILURE.getCode() : resultEnum.getCode());
        this.message = success ? ResultEnum.SUCCESS.getMessage() : (resultEnum == null ? ResultEnum.FAILURE.getMessage() : resultEnum.getMessage());
        this.data = data;
    }
}
