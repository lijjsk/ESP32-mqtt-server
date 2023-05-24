package com.Li.esp32mqttserver.response;

import com.Li.esp32mqttserver.response.ResultCode;
import lombok.Data;


import java.io.Serializable;

@Data
public class JsonResult<T> implements Serializable {
    private Boolean success;
    private Integer errorCode;
    private String errorMsg;
    private T data;
    public JsonResult() {
    }

    public JsonResult(boolean success) {
        this.success = success;
        this.errorCode = success ? ResultCode.SUCCESS.getCode() : ResultCode.FAILURE.getCode();
        this.errorMsg = success ? ResultCode.SUCCESS.getMessage() : ResultCode.FAILURE.getMessage();
    }

    public JsonResult(boolean success, ResultCode resultEnum) {
        this.success = success;
        this.errorCode = success ? ResultCode.SUCCESS.getCode() : (resultEnum == null ? ResultCode.FAILURE.getCode() : resultEnum.getCode());
        this.errorMsg = success ? ResultCode.SUCCESS.getMessage() : (resultEnum == null ? ResultCode.FAILURE.getMessage() : resultEnum.getMessage());
    }

    public JsonResult(boolean success, T data) {
        this.success = success;
        this.errorCode = success ? ResultCode.SUCCESS.getCode() : ResultCode.FAILURE.getCode();
        this.errorMsg = success ? ResultCode.SUCCESS.getMessage() : ResultCode.FAILURE.getMessage();
        this.data = data;
    }

    public JsonResult(boolean success, ResultCode resultEnum, T data) {
        this.success = success;
        this.errorCode = success ? ResultCode.SUCCESS.getCode() : (resultEnum == null ? ResultCode.FAILURE.getCode() : resultEnum.getCode());
        this.errorMsg = success ? ResultCode.SUCCESS.getMessage() : (resultEnum == null ? ResultCode.FAILURE.getMessage() : resultEnum.getMessage());
        this.data = data;
    }
}
