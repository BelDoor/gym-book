package com.gym.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gym.domain.column.SystemRole;
import com.gym.domain.entity.User;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class RoleResponse {

    @Enumerated(EnumType.STRING)
    private SystemRole roleName;


    @JsonIgnoreProperties({"firstName", "created", "changed", "isActively", "userPassword",
            "height", "gender", "surname", "birthday", "parameterGyms", "roles", "targetGyms"})
    private User user;
}
