package com.gym.controller.dto.target;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class TargetGymRequest {

    @Positive
    @NotNull
    private double targetWeight;

    @Positive
    @NotNull
    private double targetFatPercent;

    @Positive
    @NotNull
    private double targetBench;

    @Positive
    @NotNull
    private double targetSquat;

    @Positive
    @NotNull
    private double targetTraction;
}
