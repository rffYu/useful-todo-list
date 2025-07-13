package com.useful.todolist.todo.mapper;

import com.useful.todolist.todo.dto.TodoItemDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TodoItemMapper {
    @Select("SELECT * FROM todo_item WHERE todo_id = #{todoId}")
    TodoItemDTO findById(String todoId);

    @Select("SELECT * FROM todo_item WHERE user_id = #{userId}")
    List<TodoItemDTO> findByUserId(String userId);

    @Select("SELECT * FROM todo_item WHERE todo_id = #{todoId} AND user_id = #{userId}")
    TodoItemDTO findByTodoIdAndUserId(String todoId, String userId);

    @Insert("INSERT INTO todo_item (user_id, title, content, done) VALUES (#{userId}, #{title}, #{content}, #{done})")
    @Options(useGeneratedKeys = true, keyProperty = "todoId", keyColumn = "todo_id")
    void save(TodoItemDTO item);

    void saveAll(@Param("todos") List<TodoItemDTO> todos);

    @Delete("DELETE FROM todo_item WHERE todo_id = #{todoId}")
    void delete(String todoId);

    @Delete("DELETE FROM todo_item")
    void deleteAll();
}

