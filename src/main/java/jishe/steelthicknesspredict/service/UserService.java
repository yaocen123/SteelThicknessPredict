package jishe.steelthicknesspredict.service;

import jishe.steelthicknesspredict.mapper.UserMapper;
import jishe.steelthicknesspredict.pojo.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 注册用户
    public boolean registerUser(String username, String password) {
        if (userMapper.findByUsername(username) != null) {
            return false; // 用户名已存在
        }
        // 插入明文密码
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);  // 不加密，存储明文密码
        return userMapper.insertUser(user) > 0;
    }

    // 登录验证
    public boolean authenticateUser(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            return false; // 用户不存在
        }
        // 直接比较明文密码
        return password.equals(user.getPassword());
    }
}
