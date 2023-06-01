package com.gym.controller.dto.training;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class TrainingRequest {

    @Positive
    @NotNull
    private int numWeek;

    @NotNull
    private String importantPointsBlock;

    @NotNull
    private boolean actively;
}
