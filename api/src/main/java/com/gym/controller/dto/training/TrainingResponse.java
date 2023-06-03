package com.gym.controller.dto.training;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gym.domain.entity.LProgram;
import com.gym.domain.entity.Workout;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Collections;
import java.util.Set;

@Data
public class TrainingResponse {

    @Positive
    @NotNull
    private int numWeek;

    @NotNull
    private String importantPointsBlock;

    @NotNull
    private boolean actively;

    @JsonIgnoreProperties({"trainingBlock", "created", "changed"})
    private LProgram programId;

    @JsonIgnoreProperties({"trainingBlockId", "created", "changed"})
    private Set<Workout> workouts = Collections.emptySet();

}
