package com.gym.controller.dto.muscle;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class MuscleRequest {

    @Size(min = 2, max = 180)
    @NotNull
    private String muscleName;

    @NotNull
    private String muscleTXT;

}
