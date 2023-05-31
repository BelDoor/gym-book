package com.gym.domain.entity.parameter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class ParameterGymUpdate {

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
