package com.gym.controller.rest;

import com.gym.controller.dto.target.TargetGymDTO;
import com.gym.controller.dto.target.TargetGymRequest;
import com.gym.domain.entity.TargetGym;
import com.gym.domain.entity.parameter.ParameterGym;
import com.gym.domain.entity.parameter.ParameterGymUpdate;
import com.gym.service.target.TargetGymService;
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
@RequestMapping("/rest/target")
@RequiredArgsConstructor
public class TargetGymController {

    private final TargetGymService service;

    private final ModelMapper model;

    @GetMapping
    public ResponseEntity<List<TargetGymDTO>> getAllTargets() {
        List<TargetGymDTO> targetsGym = service.findAll().stream()
                .map(this::convertToTargetGymDTO).collect(Collectors.toList());;
        return new ResponseEntity<>(targetsGym, HttpStatus.OK);
    }

    @GetMapping("/{target_id}")
    public ResponseEntity<TargetGymDTO> getFindById(@PathVariable("target_id") Long id) {

        TargetGymDTO targetGymDTO =
                convertToTargetGymDTO(service.findById(id));

        return new ResponseEntity<>(targetGymDTO, HttpStatus.OK);
    }

    @PostMapping("/{user_id}")
    public ResponseEntity<HttpStatus> createTarget(@PathVariable("user_id") Long userId,
                                                      @RequestBody @Valid TargetGymRequest target) {

        service.save(userId, convertToTargetGym(target));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/{target_id}")
    public ResponseEntity<HttpStatus> updateTarget(@PathVariable("target_id") Long id,
                                                       @Valid @RequestBody TargetGymRequest target,
                                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //TODO
        }

        TargetGym updatedParameter = service.updateTargetGym(id, convertToTargetGym(target));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRole(@PathVariable("id") Long id) {

        service.deleteTarget(id);

        return ResponseEntity.ok(HttpStatus.OK);

    }

    private TargetGymDTO convertToTargetGymDTO(TargetGym targetGym){
        return model.map(targetGym, TargetGymDTO.class);
    }

    private TargetGym convertToTargetGym(TargetGymRequest target){
        return model.map(target, TargetGym.class);
    }

}
