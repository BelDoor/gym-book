package com.gym.controller.dto.workoutSet;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;

@Data
public class WorkoutSetRequest {

    @Positive
    @NotNull
    private Integer quantitySet;


    @Positive
    @NotNull
    private Integer maxRepetitions;


    @Positive
    @NotNull
    private Integer minRepetitions;


    @Positive
    @NotNull
    private Double maxWeight;


    @Positive
    @NotNull
    private Double minWeight;


    @NotNull
    private Timestamp restTime;

}
