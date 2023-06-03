package com.gym.controller.dto.user;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gym.domain.column.Gender;
import com.gym.domain.entity.parameter.ParameterGym;
import com.gym.domain.entity.Role;
import com.gym.domain.entity.TargetGym;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@Data
public class UserDTO {

    @Size(min = 2, max = 100)
    @NotEmpty
    private String firstName;

    @Size(min = 2, max = 100)
    @NotEmpty
    private String surname;

    @NotNull
    private Timestamp birthday;

    @NotNull
    @Positive
    private Integer height;

    @NotNull
    @Enumerated(EnumType.STRING)
//    @Pattern(regexp = "") TODO
    private Gender gender;

    @NotEmpty
 //   @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    private String userPassword;

    @Email
 //   @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z]).{6,}$")
    @NotEmpty(message = "Should not be empty")
    private String userEmail;

    @NotNull
    @Positive
    private Long userPhone;

    @JsonIgnoreProperties({"user"})
    private Set<Role> roles = Collections.emptySet();

    @JsonIgnoreProperties({"userId", "program"})
    private Set<ParameterGym> parameterGyms = Collections.emptySet();

    @JsonIgnoreProperties({"userId"})
    private Set<TargetGym> targetGyms  = Collections.emptySet();

}
