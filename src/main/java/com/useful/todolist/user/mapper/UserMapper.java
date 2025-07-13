package com.useful.todolist.user.mapper;

import com.useful.todolist.user.dto.UserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE user_id = #{userId}")
    UserDTO findByUserId(String userId);

    @Select("SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END FROM user WHERE user_id = #{userId}")
    boolean existsByUserId(String userId);

    @Insert("INSERT INTO user (user_id, user_name, password, role_id, group_id) " +
        "VALUES (#{userId}, #{userName}, #{password}, #{roleId}, #{groupId})")
    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
    void save(UserDTO user);

    void saveAll(@Param("users") List<UserDTO> users);

    @Delete("DELETE FROM user WHERE user_id = #{userId}")
    void delete(String userId);

    @Delete("DELETE FROM user")
    void deleteAll();
}
