package com.Li.esp32mqttserver.config.authentication;
import com.Li.esp32mqttserver.response.JsonResult;
import com.Li.esp32mqttserver.response.ResultCode;
import com.Li.esp32mqttserver.response.ResultTool;
import com.alibaba.fastjson.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //返回json数据
        JsonResult result = null;
        if (exception instanceof BadCredentialsException) {
            //密码错误
            result = ResultTool.fail(ResultCode.USER_CREDENTIALS_ERROR);
        } else if (exception instanceof InternalAuthenticationServiceException) {
            //用户不存在
            result = ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
        else{
            //其他错误
            result = ResultTool.fail(ResultCode.FAILURE);
            exception.printStackTrace();
        }
        //处理编码方式，防止中文乱码的情况
        response.setContentType("text/json;charset=utf-8");
        //塞到HttpServletResponse中返回给前台
        response.getWriter().write(JSON.toJSONString(result));
    }
}
