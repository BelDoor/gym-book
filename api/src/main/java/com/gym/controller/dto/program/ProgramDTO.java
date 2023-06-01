package com.gym.controller.dto.program;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gym.domain.entity.TargetGym;
import com.gym.domain.entity.parameter.ParameterGym;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class ProgramDTO {

    @NotNull
    private Timestamp dataStart;

    @NotNull
    private Timestamp dataEnd;

    @NotNull
    private String gymProgram;

    @NotNull
    private boolean actively;


    @JsonIgnoreProperties({"target_id", "user_id", "created", "changed", "program"})
    private TargetGym targetGymId;


    @JsonIgnoreProperties({"user_id", "created", "changed", "program"})
    private ParameterGym parameterGymId;

}
