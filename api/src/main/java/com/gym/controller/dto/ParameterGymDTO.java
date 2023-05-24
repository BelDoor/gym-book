package com.gym.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gym.domain.entity.User;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class ParameterGymDTO {

    @NotNull
    @Positive
    private Double weight;

    @NotNull
    @Positive
    private Double fatPercent;

    @NotNull
    @Positive
    private Double maxBench;

    @NotNull
    @Positive
    private Double maxSquat;

    @NotNull
    @Positive
    private Double maxTraction;


    @Column(nullable = false)
    private Timestamp created = Timestamp.valueOf(LocalDateTime.now());

    @JsonIgnoreProperties({"firstName", "created", "changed", "isActively", "userPassword",
            "gender", "surname", "birthday", "parameterGyms", "roles", "userPhone"})
    private User user;

}
