package com.gym.controller.dto.parameter;

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
public class ParameterGymRequest {

    @Positive
    @NotNull
    private Double weight;

    @Positive
    @NotNull
    private Double fatPercent;

    @Positive
    @NotNull
    private Double maxBench;

    @Positive
    @NotNull
    private Double maxSquat;

    @Positive
    @NotNull
    private Double maxTraction;

}
