package com.gym.controller.rest;

import com.gym.controller.dto.ParameterGymDTO;
import com.gym.controller.dto.RoleDTO;
import com.gym.domain.entity.ParameterGym;
import com.gym.domain.entity.Role;
import com.gym.service.parameter.ParameterGymService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/parameters")
@RequiredArgsConstructor
public class ParameterGymController{

    private final ParameterGymService service;
    private final ModelMapper model;

    @GetMapping
    public ResponseEntity<List<ParameterGymDTO>> getAllParameters() {
        List<ParameterGymDTO> parametersGym = service.findAll().stream()
                .map(this::convertToParameterGymDTO).collect(Collectors.toList());;
        return new ResponseEntity<>(parametersGym, HttpStatus.OK);
    }

    @GetMapping("/{role_id}")
    public ResponseEntity<ParameterGymDTO> getFindById(@PathVariable("role_id") Long id) {

        ParameterGymDTO parameterGymDTO =
                convertToParameterGymDTO(service.findById(id));

        return new ResponseEntity<>(parameterGymDTO, HttpStatus.OK);
    }

    @PostMapping("/{user_id}")
    public ResponseEntity<HttpStatus> createParameter(@PathVariable("user_id") Long userId,
                                                 @RequestBody @Valid ParameterGymDTO parameterGymDTO) {

        service.save(userId, convertToParameterGym(parameterGymDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRole(@PathVariable("id") Long id) {

        service.deleteParameter(id);

        return ResponseEntity.ok(HttpStatus.OK);

    }

    private ParameterGymDTO convertToParameterGymDTO(ParameterGym parameterGym){
        return model.map(parameterGym, ParameterGymDTO.class);
    }

    private ParameterGym convertToParameterGym(ParameterGymDTO parameterGymDTO){
        return model.map(parameterGymDTO, ParameterGym.class);
    }
}
