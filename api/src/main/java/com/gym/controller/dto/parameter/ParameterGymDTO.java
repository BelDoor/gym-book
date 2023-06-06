package com.gym.controller.dto.parameter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gym.domain.entity.LProgram;
import com.gym.domain.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

@Data
@Schema(description = "An object with information parameterGymDTO outputs about the Parameter User")
public class ParameterGymDTO {

    @NotNull
    @Positive
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            example = "95.5", type = "Double", description = "Parameter -> weight")
    private Double weight;

    @NotNull
    @Positive
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            example = "15", type = "Double", description = "Parameter -> body fat percentage")
    private Double fatPercent;

    @NotNull
    @Positive
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            example = "95", type = "Double", description = "Parameter -> maximum bench press weight")
    private Double maxBench;

    @NotNull
    @Positive
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            example = "120.5", type = "Double", description = "Parameter -> maximum squat weight")
    private Double maxSquat;

    @NotNull
    @Positive
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            example = "85.0", type = "Double", description = "Parameter -> maximum traction weight")
    private Double maxTraction;


    @Column(nullable = false)
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            example = "857026419000", type = "Double", description = "Parameter -> measurement creation date")
    private Timestamp created = Timestamp.valueOf(LocalDateTime.now());

    @JsonIgnoreProperties({"created", "changed", "isActively", "userPassword",
            "gender", "birthday", "parameterGyms", "roles", "userPhone"})
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            type = "User", description = "Displays first Name, surname, email and user id")
    private User user;

    @JsonIgnoreProperties({"trainingBlock", "targetGymId",
            "parameterGymId"})
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            type = "LProgram", description = "Outputs LProgram except training Block target Gym id parameter Gym Id")
    private Set<LProgram> program = Collections.emptySet();
}
