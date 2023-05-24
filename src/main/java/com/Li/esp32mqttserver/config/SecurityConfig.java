package com.Li.esp32mqttserver.config;

import com.Li.esp32mqttserver.config.authentication.CustomizeAuthenticationEntryPoint;
import com.Li.esp32mqttserver.config.authentication.CustomizeAuthenticationFailureHandler;
import com.Li.esp32mqttserver.config.authentication.CustomizeAuthenticationSuccessHandler;
import com.Li.esp32mqttserver.config.authentication.CustomizeLogoutSuccessHandler;
import com.Li.esp32mqttserver.service.Impl.MyUserDetailsServiceImpl;
import com.Li.esp32mqttserver.util.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//security配置类
@Configuration
@ComponentScan("com.Li.esp32mqttserver.config")
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    MyUserDetailsServiceImpl userDetailsService;
    @Autowired
    CustomizeAuthenticationEntryPoint customizeAuthenticationEntryPoint;
    @Autowired
    CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;
    @Autowired
    CustomizeAuthenticationFailureHandler customizeAuthenticationFailureHandler;
    @Autowired
    CustomizeLogoutSuccessHandler customizeLogoutSuccessHandler;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //把token校验过滤器添加到过滤器链中
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http
                .authorizeHttpRequests((authorize) -> authorize
                        //定义不需要认证就可以访问的界面,测试时可以选择开放需要的controller接口
                        .requestMatchers("/mylogin","/static/**","/","/index","/Goods/**","/Facility/**","/register").permitAll()
                        .anyRequest().authenticated());//其余所有请求都需要登录认证才能访问
        http
                //启用默认登录页面
                .formLogin()
                .loginPage("/mylogin")
                //指定自定义form表单请求的路径
                .loginProcessingUrl("/loginForm").usernameParameter("name").passwordParameter("pass")
                //.defaultSuccessUrl("/success")
                .successForwardUrl("/success")
                //设置了登入登出的Handler,优先响应Handler
                .failureUrl("/fail")
                //自定义认证成功或者失败的返回json
                .successHandler(customizeAuthenticationSuccessHandler)
                .failureHandler(customizeAuthenticationFailureHandler)
                .permitAll();//允许所有用户

        http
                .logout()
                //自定义退出url
                .logoutUrl("/mylogout")
                .logoutSuccessUrl("/mylogin")
                //设置了登入登出的Handler,优先响应Handler
                .logoutSuccessHandler(customizeLogoutSuccessHandler)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")//登出之后删除cookie
                .permitAll();

                //屏蔽默认重定向登录页面
        http
                .exceptionHandling()
                .authenticationEntryPoint(customizeAuthenticationEntryPoint);//未登录

        //允许跨域
        http.cors();
        //关闭csrf校验
        http.csrf().disable();
        return http.build();
    }
    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception{
        AuthenticationManager authenticationManager = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
        return authenticationManager;

    }
}
