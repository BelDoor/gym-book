package com.gym.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gym.domain.column.SystemRole;
import com.gym.domain.entity.Role;
import com.gym.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Positive;

@Data
public class RoleDTO {

    @Enumerated(EnumType.STRING)
    private SystemRole roleName = SystemRole.ROLE_USER;


    @JsonIgnoreProperties({"firstName", "created", "changed", "isActively", "userPassword",
            "height", "gender", "surname", "birthday", "parameterGyms", "roles"})
    private User user;

}
