package com.gym.controller.dto.log_workout;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class LogWorkoutRequest {

    @NotNull
    @Positive
    private Double logWeight;

    @NotNull
    @Positive
    private Integer logRepetitions;

}
