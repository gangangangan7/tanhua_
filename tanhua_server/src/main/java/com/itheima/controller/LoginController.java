package com.itheima.controller;

import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pojo.User;

import java.util.Map;

/**
 * @program: tanhua
 * @description
 * @author: Zi'chao
 * @create: 2021-05-03 19:58
 **/
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/findUser")
    public ResponseEntity findUser(String phone) {
        User user = userService.findUser(phone);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity sendValidateCode(@RequestBody Map<String,String> param){
        String phone = param.get("phone");
        userService.sendValidateCode(phone);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/loginVerification")
    public ResponseEntity loginVerification(@RequestBody Map<String,String> param){
        //调用业务 登录
        Map<String, Object> stringObjectMap = userService.loginVerification(param);
        return ResponseEntity.ok(stringObjectMap);

    }
}
