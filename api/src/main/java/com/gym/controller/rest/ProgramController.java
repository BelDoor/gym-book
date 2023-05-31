package com.gym.controller.rest;

import com.gym.service.program.LProgramService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/program")
@RequiredArgsConstructor
public class ProgramController {

    private final LProgramService service;

    private final ModelMapper model;


}
