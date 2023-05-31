package com.gym.controller.dto.program;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class ProgramSaveRequest {

    @NotNull
    private Timestamp dataStart;

    @NotNull
    private Timestamp dataEnd;

    @NotNull
    private String gymProgram;
}
