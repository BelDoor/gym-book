package com.gym.controller.rest;

import com.gym.controller.dto.program.ProgramDTO;
import com.gym.controller.dto.program.ProgramRequest;
import com.gym.domain.entity.LProgram;
import com.gym.domain.entity.parameter.ParameterGym;
import com.gym.domain.entity.parameter.ParameterGymUpdate;
import com.gym.repository.LProgrameRepository;
import com.gym.repository.ParameterGymRepository;
import com.gym.service.program.LProgramService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/program")
@RequiredArgsConstructor
public class ProgramController {

    private final LProgramService service;

    private final ModelMapper model;

    private final LProgrameRepository repository;

    @GetMapping
    public ResponseEntity<List<ProgramDTO>> getAllPrograms() {
        List<ProgramDTO> programDTO = service.findAll().stream()
                .map(this::converterToProgramDTO).collect(Collectors.toList());
        ;
        return new ResponseEntity<>(programDTO, HttpStatus.OK);
    }

    @GetMapping("/{program_id}")
    public ResponseEntity<ProgramDTO> getFindById(@PathVariable("program_id") Long id) {

        ProgramDTO program =
                converterToProgramDTO(service.findById(id));

        return new ResponseEntity<>(program, HttpStatus.OK);
    }

    @PostMapping("/{parameter_id}/{target_id}")
    public ResponseEntity<HttpStatus> createParameter(@PathVariable("parameter_id") Long parameterId,
                                                      @PathVariable("target_id") Long targetId,
                                                      @RequestBody @Valid ProgramRequest program) {

        service.save(parameterId, targetId, converterToLProgram(program));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateProgram(@PathVariable Long id,
                                                       @Valid @RequestBody ProgramRequest program,
                                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //TODO
        }

        LProgram updatedProgram = service.updateLProgram(id, converterToLProgram(program));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProgram(@PathVariable Long id){
        repository.deleteById(id);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private ProgramDTO converterToProgramDTO(LProgram program) {
        return model.map(program, ProgramDTO.class);
    }

    private LProgram converterToLProgram(ProgramRequest program) {
        return model.map(program, LProgram.class);
    }
}
