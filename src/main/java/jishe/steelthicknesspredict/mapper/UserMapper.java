package jishe.steelthicknesspredict.mapper;

import jishe.steelthicknesspredict.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    // 根据用户名查询用户
    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUsername(String username);

    // 插入新用户
    @Insert("INSERT INTO users (username, password, create_time) VALUES (#{username}, #{password}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(User user);
}
