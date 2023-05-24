package com.Li.esp32mqttserver.controller;

import com.Li.esp32mqttserver.domain.admin;
import com.Li.esp32mqttserver.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/login")
    public boolean Login(@RequestBody String name,@RequestBody String pass){
        admin admin = adminService.getAdminByNameAndPass(name,pass);
        return admin != null;
    }

    @PostMapping(value = "")
    public boolean register(@RequestBody String name,@RequestBody String pass){
        if(adminService.getAdminByNameAndPass(name,pass) != null){
            return false;
        }else{
            admin admin = new admin();
            admin.setName(name);
            admin.setPass(pass);
            return true;
        }
    }
}
