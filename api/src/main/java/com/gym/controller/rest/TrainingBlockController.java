package com.gym.controller.rest;

import com.gym.controller.dto.parameter.ParameterGymRequest;
import com.gym.controller.dto.target.TargetGymDTO;
import com.gym.controller.dto.training.TrainingRequest;
import com.gym.controller.dto.training.TrainingResponse;
import com.gym.domain.entity.TargetGym;
import com.gym.domain.entity.TrainingBlock;
import com.gym.domain.entity.parameter.ParameterGym;
import com.gym.domain.entity.parameter.ParameterGymUpdate;
import com.gym.service.target.TargetGymService;
import com.gym.service.training.TrainingBlockService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/rest/training")
@RequiredArgsConstructor
public class TrainingBlockController {

    private final TrainingBlockService service;

    private final ModelMapper model;

    @GetMapping
    public ResponseEntity<List<TrainingResponse>> getAllTargets() {
        List<TrainingResponse> targetsGym = service.findAll().stream()
                .map(this::convertToTraining).collect(Collectors.toList());;
        return new ResponseEntity<>(targetsGym, HttpStatus.OK);
    }

    @GetMapping("/{training_id}")
    public ResponseEntity<TrainingBlock> getFindById(@PathVariable("training_id") Long id) {

        TrainingBlock trainingBlock = service.findById(id);

        return new ResponseEntity<>(trainingBlock, HttpStatus.OK);
    }

    @PostMapping("/{program_id}")
    public ResponseEntity<HttpStatus> createParameter(@PathVariable("program_id") Long programId,
                                                      @RequestBody @Valid TrainingRequest training) {

        service.save(programId, convertToTrainingBlock(training));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/{training_id}")
    public ResponseEntity<HttpStatus> updateParameters(@PathVariable("training_id") Long id,
                                                       @Valid @RequestBody TrainingRequest training,
                                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //TODO
        }

        TrainingBlock trainingBlock = service.updateTrainingBlock(id, convertToTrainingBlock(training));

        return ResponseEntity.ok(HttpStatus.OK);
    }


    private TrainingResponse convertToTraining(TrainingBlock training){
        return model.map(training, TrainingResponse.class);
    }

    private TrainingBlock convertToTrainingBlock(TrainingRequest training){
        return model.map(training, TrainingBlock.class);
    }



}
