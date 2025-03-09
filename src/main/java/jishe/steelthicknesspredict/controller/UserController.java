package jishe.steelthicknesspredict.controller;

import jishe.steelthicknesspredict.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")  // 允许前端在不同域时访问
public class UserController {

    private final UserService userService;

    // 注入 UserService
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 登录接口
    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam String username, @RequestParam String password) {
        boolean success = userService.authenticateUser(username, password);
        Map<String, Object> response = new HashMap<>();
        if (success) {
            response.put("code", 200);
            response.put("message", "登录成功");
        } else {
            response.put("code", 401);
            response.put("message", "用户名或密码错误");
        }
        return response;
    }

    // 注册接口
    @PostMapping("/register")
    public Map<String, Object> register(@RequestParam String username, @RequestParam String password) {
        boolean success = userService.registerUser(username, password);
        Map<String, Object> response = new HashMap<>();
        if (success) {
            response.put("code", 200);
            response.put("message", "注册成功");
        } else {
            response.put("code", 400);
            response.put("message", "用户名已存在");
        }
        return response;
    }
}
