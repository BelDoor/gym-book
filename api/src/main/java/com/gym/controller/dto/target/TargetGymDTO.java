package com.gym.controller.dto.target;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gym.domain.entity.LProgram;
import com.gym.domain.entity.User;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

@Data
public class TargetGymDTO {

    @NotNull
    @Positive
    private double targetWeight;

    @NotNull
    @Positive
    private double targetFatPercent;

    @NotNull
    @Positive
    private double targetBench;

    @NotNull
    @Positive
    private double targetSquat;

    @NotNull
    @Positive
    private double targetTraction;


    @Column(nullable = false)
    private Timestamp created = Timestamp.valueOf(LocalDateTime.now());

    @JsonIgnoreProperties({"firstName", "created", "changed", "isActively", "userPassword",
            "gender", "surname", "birthday", "parameterGyms", "roles", "userPhone", "targetGyms"})
    private User user;

    @JsonIgnoreProperties({"targetGymId"})
    private Set<LProgram> program = Collections.emptySet();
}
