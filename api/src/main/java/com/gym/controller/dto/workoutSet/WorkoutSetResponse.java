package com.gym.controller.dto.workoutSet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gym.domain.entity.CExercise;
import com.gym.domain.entity.LogWorkout;
import com.gym.domain.entity.Workout;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@Data
public class WorkoutSetResponse {

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

    @JsonIgnoreProperties({"blockId", "created", "changed", "workoutSets"})
    private Workout workout;

    @JsonIgnoreProperties({"workoutSets"})
    private CExercise exercise;

    @JsonIgnoreProperties({"created", "changed", "workoutSet"})
    private Set<LogWorkout> logWorkout = Collections.emptySet();

}
