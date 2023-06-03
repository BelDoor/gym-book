package com.gym.controller.rest;

import com.gym.controller.dto.workoutSet.WorkoutSetRequest;
import com.gym.controller.dto.workoutSet.WorkoutSetResponse;
import com.gym.domain.entity.WorkoutSet;
import com.gym.service.workout_set.WorkoutSetService;
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
@RequestMapping("/rest/workout_set")
@RequiredArgsConstructor
public class WorkoutSetController {

    private final WorkoutSetService service;

    private final ModelMapper model;

    @GetMapping
    public ResponseEntity<List<WorkoutSetResponse>> getAllWorkoutSet() {
        List<WorkoutSetResponse> workoutSets = service.findAll().stream()
                .map(this::convertToWorkoutSetResponse).collect(Collectors.toList());
        return new ResponseEntity<>(workoutSets, HttpStatus.OK);
    }


    @GetMapping("/{workout_set_id}")
    public ResponseEntity<WorkoutSet> getFindById(@PathVariable("workout_set_id") Long id) {

        WorkoutSet workoutSet = service.findById(id);

        return new ResponseEntity<>(workoutSet, HttpStatus.OK);
    }

    @PostMapping("/{workout_id}/{exercise_id}")
    public ResponseEntity<HttpStatus> createWorkoutSet(@PathVariable("workout_id") Long workoutId,
                                                       @PathVariable("exercise_id") Long exerciseId,
                                                       @RequestBody @Valid WorkoutSetRequest workoutSet) {

        service.save(workoutId, exerciseId, convertToWorkoutSet(workoutSet));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/{workout_set_id}")
    public ResponseEntity<HttpStatus> updateWorkoutSet(@PathVariable("workout_set_id") Long id,
                                                       @Valid @RequestBody WorkoutSetRequest workoutSet,
                                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //TODO
        }

        WorkoutSet workout = service.updateWorkoutSet(id, convertToWorkoutSet(workoutSet));

        return ResponseEntity.ok(HttpStatus.OK);
    }


    private WorkoutSet convertToWorkoutSet(WorkoutSetRequest workoutSet) {
        return model.map(workoutSet, WorkoutSet.class);
    }

    private WorkoutSetResponse convertToWorkoutSetResponse(WorkoutSet workoutSet) {
        return model.map(workoutSet, WorkoutSetResponse.class);
    }

}
