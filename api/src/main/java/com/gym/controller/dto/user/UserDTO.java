package com.gym.controller.dto.user;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gym.domain.column.Gender;
import com.gym.domain.entity.parameter.ParameterGym;
import com.gym.domain.entity.Role;
import com.gym.domain.entity.TargetGym;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@Data
@Schema(description = "An object with information userDto outputs about the user")
public class UserDTO {

    @Size(min = 2, max = 80)
    @NotEmpty
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            example = "Helga", type = "string", description = "user first name")
    private String firstName;

    @Size(min = 2, max = 80)
    @NotEmpty
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            example = "Pakli", type = "string", description = "user surname")
    private String surname;

    @NotNull
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            example = "857026419000", type = "timestamp", description = "user birth date")
    @Pattern(regexp = "[\\d]+0{3}")
    private Timestamp birthday;

    @NotNull
    @Positive
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            example = "185", type = "Integer", description = "user height")
    private Integer height;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            example = "NOT_SELECTED", type = "Gender", description = "user gender")
    private Gender gender;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z]).{6,}$")
    @Schema(example = "QWERTY23", type = "String", description = "User password")
    private String userPassword;

    @Email
    @NotEmpty(message = "Should not be empty")
    @Schema(example = "Bonjo2021@gmail.com", type = "String", description = "User email")
    private String userEmail;

    @NotNull
    @Positive
    @Schema(example = "375238297231", type = "Long", description = "User phone")
    private Long userPhone;

    @JsonIgnoreProperties({"user"})
    @Schema(type = "Role", description = "We display the roles belonging to the user, without schema user")
    private Set<Role> roles = Collections.emptySet();

    @JsonIgnoreProperties({"userId", "program"})
    @Schema(type = "ParameterGym",
            description = "We display the Parameters Gym belonging to the user, without schema user")
    private Set<ParameterGym> parameterGyms = Collections.emptySet();

    @JsonIgnoreProperties({"userId"})
    @Schema(type = "Role", description = "We display the Targets Gym belonging to the user, without schema user")
    private Set<TargetGym> targetGyms = Collections.emptySet();

}
