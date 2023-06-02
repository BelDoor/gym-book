package com.gym.controller.dto.exercise;


import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ExerciseRequest {

    @Size(min = 2, max = 80)
    @NotNull
    private String exerciseName;

    @NotNull
    private String exerciseTXT;

}
