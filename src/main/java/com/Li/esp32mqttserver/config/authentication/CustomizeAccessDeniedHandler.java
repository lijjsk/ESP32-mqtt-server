package com.Li.esp32mqttserver.config.authentication;

import com.Li.esp32mqttserver.response.JsonResult;
import com.Li.esp32mqttserver.response.ResultEnum;
import com.Li.esp32mqttserver.response.ResultTool;
import com.alibaba.fastjson.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class CustomizeAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        JsonResult result = ResultTool.fail(ResultEnum.NO_PERMISSION);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(result));
        System.out.println(accessDeniedException);
        System.out.println("进入了授权失败");
    }
}
