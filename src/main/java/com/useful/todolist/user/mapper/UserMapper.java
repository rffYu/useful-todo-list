package com.useful.todolist.user.mapper;

import com.useful.todolist.user.dto.UserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE user_id = #{userId}")
    UserDTO findByUserId(String userId);

    @Select("SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END FROM user WHERE user_id = #{userId}")
    boolean existsByUserId(String userId);

    @Insert("INSERT INTO user (user_id, user_name, password, role_id, group_id) " +
        "VALUES (#{userId}, #{userName}, #{password}, #{roleId}, #{groupId})")
    @Options(useGeneratedKeys = true, keyProperty = "user_id", keyColumn = "user_id")
    void save(UserDTO user);

    @Delete("DELETE FROM user WHERE user_id = #{userId}")
    void delete(String userId);
}
