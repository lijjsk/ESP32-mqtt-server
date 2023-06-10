package com.Li.esp32mqttserver.config.authentication;

import com.Li.esp32mqttserver.domain.LoginUser;
import com.Li.esp32mqttserver.redis.RedisCache;
import com.Li.esp32mqttserver.util.JwtUtil;
import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        //解析token
        String userid;
        //System.out.println("当前用户token为"+token);
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        //从redis中获取用户信息
        String redisKey = "login:" + userid;
        //解决从redis中取出的对象无法重新转换为LoginUser对象的问题，通过将对象转化为JSON类型字符串再重新转化为LoginUser
        Object object = redisCache.getCacheObject(redisKey);
        LoginUser loginUser;
        if (object instanceof LoginUser){
            loginUser = (LoginUser) object;
        }else{
            loginUser = JSON.parseObject(JSON.toJSON(object).toString(), LoginUser.class);
        }
        //System.out.println("当前用户信息为"+loginUser);
        //错误代码
        //LoginUser loginUser = redisCache.getCacheObject(redisKey);
        if(Objects.isNull(loginUser)){
            throw new RuntimeException("用户未登录");
        }
        //封装Authentication对象存入SecurityContextHolder
        //不配置权限,只要token正确就允许登录
        //System.out.println(loginUser.getPassword());
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser,null,null);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        try{
            filterChain.doFilter(request, response);
            System.out.println("放行成功");
        }catch (Exception e){
            System.out.println("放行失败");
            e.printStackTrace();
        }

    }
}