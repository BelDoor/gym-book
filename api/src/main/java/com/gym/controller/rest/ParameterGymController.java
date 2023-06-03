package com.gym.controller.rest;

import com.gym.controller.dto.parameter.ParameterGymDTO;
import com.gym.controller.dto.parameter.ParameterGymRequest;
import com.gym.domain.entity.parameter.ParameterGym;
import com.gym.domain.entity.parameter.ParameterGymUpdate;
import com.gym.service.parameter.ParameterGymService;
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
@RequestMapping("/rest/parameters")
@RequiredArgsConstructor
public class ParameterGymController {

    private final ParameterGymService service;

    private final ModelMapper model;

    @GetMapping
    public ResponseEntity<List<ParameterGymDTO>> getAllParameters() {
        List<ParameterGymDTO> parametersGym = service.findAll().stream()
                .map(this::convertToParameterGymDTO).collect(Collectors.toList());
        return new ResponseEntity<>(parametersGym, HttpStatus.OK);
    }

    @GetMapping("/{parameter_id}")
    public ResponseEntity<ParameterGymDTO> getFindById(@PathVariable("parameter_id") Long id) {

        ParameterGymDTO parameterGymDTO =
                convertToParameterGymDTO(service.findById(id));

        return new ResponseEntity<>(parameterGymDTO, HttpStatus.OK);
    }

    @PostMapping("/{user_id}")
    public ResponseEntity<HttpStatus> createParameter(@PathVariable("user_id") Long userId,
                                                      @RequestBody @Valid ParameterGymRequest parameter) {

        service.save(userId, convertToParameterGymRequest(parameter));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateParameters(@PathVariable Long id,
                                                       @Valid @RequestBody ParameterGymUpdate parameterGymUpdate,
                                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //TODO
        }

        ParameterGym updatedParameter = service.updateParameterGym(id, parameterGymUpdate);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteParameter(@PathVariable("id") Long id) {

        service.deleteParameter(id);

        return ResponseEntity.ok(HttpStatus.OK);

    }

    private ParameterGymDTO convertToParameterGymDTO(ParameterGym parameterGym) {
        return model.map(parameterGym, ParameterGymDTO.class);
    }

    private ParameterGym convertToParameterGymRequest(ParameterGymRequest parameter) {
        return model.map(parameter, ParameterGym.class);
    }
}
