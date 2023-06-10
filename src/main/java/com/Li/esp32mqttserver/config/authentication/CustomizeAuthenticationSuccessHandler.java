package com.Li.esp32mqttserver.config.authentication;
import com.Li.esp32mqttserver.domain.User;
import com.Li.esp32mqttserver.redis.RedisCache;
import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.Li.esp32mqttserver.domain.LoginUser;
import com.Li.esp32mqttserver.response.JsonResult;
import com.Li.esp32mqttserver.response.ResultTool;
import com.Li.esp32mqttserver.util.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.json.Json;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //如果认证通过，使用user生成jwt  jwt存入Response返回
        //认证通过，拿到这个当前登录用户信息
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //获取当前用户的userid
        String userid = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userid);
        User user = loginUser.getUser();
        //在响应头中增加token
        response.addHeader("token", jwt);
        //处理编码方式，防止中文乱码的情况
        Map<String, Object> map = new HashMap<>();
        map.put("token",jwt);
        //把完整的用户信息存入redis  userid为key   用户信息为value
        redisCache.setCacheObject("login:"+userid, loginUser);
        response.setContentType("application/json;charset=UTF-8");
        //返回json数据
        JsonResult result = ResultTool.success(user);
        map.put("data",result);
        //将token信息写入
        response.getWriter().write(objectMapper.writeValueAsString(map));
//        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
