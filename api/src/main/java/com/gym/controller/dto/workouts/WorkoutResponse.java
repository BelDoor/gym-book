package com.gym.controller.dto.workouts;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class WorkoutResponse {

    @Positive
    @NotNull
    private Integer numTraining;

    @NotNull
    private String targetWorkout;
}
