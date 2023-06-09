package com.Li.esp32mqttserver.response;

//统一JSON返回结果构造器
public class ResultTool {
    public static JsonResult success() {
        return new JsonResult(true);
    }

    public static <T> JsonResult<T> success(T data) {
        return new JsonResult<>(true, data);
    }

    public static JsonResult fail() {
        return new JsonResult(false);
    }
    public static JsonResult fail(ResultEnum resultEnum){
        return new JsonResult(false,resultEnum);
    }
}
