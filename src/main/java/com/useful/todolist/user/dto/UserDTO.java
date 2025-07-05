package com.useful.todolist.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.useful.todolist.user.constants.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @JsonProperty("userId")
    private String userId = "";

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("roleId")
    private Role roleId = Role.USER;

    @JsonProperty("groupId")
    private String groupId = "";
}

