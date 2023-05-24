package com.Li.esp32mqttserver.service;

import com.Li.esp32mqttserver.domain.User;
import com.Li.esp32mqttserver.response.JsonResult;
import com.Li.esp32mqttserver.response.ResponseResult;

public interface UserService {
    JsonResult login(User user);
    JsonResult registerUser(User user);
    JsonResult logout();
    JsonResult update(User user);
    JsonResult getUserInfo(Long userid);
}
