package com.Li.esp32mqttserver.config.authentication;

import com.Li.esp32mqttserver.response.JsonResult;
import com.Li.esp32mqttserver.response.ResultTool;
import com.alibaba.fastjson.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //返回json数据
        JsonResult result = ResultTool.success();
        //处理编码方式，防止中文乱码的情况
        response.setContentType("text/json;charset=utf-8");
        //塞到HttpServletResponse中返回给前台
        response.getWriter().write(JSON.toJSONString(result));
    }
}
