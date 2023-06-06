package com.gym.controller.dto.user;

import com.gym.domain.column.Gender;
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

@Data
@Schema(description = "Object with userDto information on registration stage")
public class UserRequest {

    @Size(min = 2, max = 80)
    @NotEmpty
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            example = "Helga", type = "string", description = "user first name")
    private String firstName;

    @Size(min = 2, max = 80)
    @NotEmpty
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            example = "Paklit", type = "string", description = "user surname")
    private String surname;

    @NotNull
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            example = "857026419000", type = "timestamp", description = "user birth date")
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
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    @Schema(example = "QWERty23", type = "String", description = "User password")
    private String userPassword;

    @Email
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z]).{6,}$")
    @Schema(example = "Bonjo2021@gmail.com", type = "String", description = "User email")
    @NotEmpty(message = "Should not be empty")
    private String userEmail;

    @NotNull
    @Positive
    @Schema(example = "375238297231", type = "Long", description = "User phone")
    private Long userPhone;

}
