package com.Li.esp32mqttserver.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户表(User)实体类
 *
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User{

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 密码
     */
    private String pass;
    /**
     * 账号状态（0正常 1停用）
     */
    private String status;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 用户性别（0男，1女，2未知）
     */
    private String sex;
}

