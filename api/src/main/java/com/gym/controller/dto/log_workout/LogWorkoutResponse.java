package com.gym.controller.dto.log_workout;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gym.domain.entity.WorkoutSet;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class LogWorkoutResponse {

    @NotNull
    @Positive
    private Double logWeight;

    @NotNull
    @Positive
    private Integer logRepetitions;

    @NotNull
    @Positive
    private Integer numSet;

    private WorkoutSet workoutSet;
}
