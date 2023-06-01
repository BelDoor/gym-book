package com.gym.controller.dto.workouts;

import lombok.Data;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Data
public class WorkoutRequest {

    @Positive
    @NotNull
    private Integer numTraining;

    @NotNull
    private String targetWorkout;
}
