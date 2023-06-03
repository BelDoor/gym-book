package com.gym.controller.rest;

import com.gym.controller.dto.log_workout.LogWorkoutRequest;
import com.gym.controller.dto.log_workout.LogWorkoutResponse;
import com.gym.domain.entity.LogWorkout;
import com.gym.service.log_workout.LogWorkoutService;
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
@RequestMapping("/rest/log_workout")
@RequiredArgsConstructor
public class LogWorkoutController {

    private final LogWorkoutService service;

    private final ModelMapper model;

    @GetMapping
    public ResponseEntity<List<LogWorkoutResponse>> getAllLogWorkout() {
        List<LogWorkoutResponse> logWorkoutResponses = service.findAll().stream()
                .map(this::convertToLogWorkoutResponse).collect(Collectors.toList());
        return new ResponseEntity<>(logWorkoutResponses, HttpStatus.OK);
    }

    @GetMapping("/{log_workout_id}")
    public ResponseEntity<LogWorkoutResponse> getFindById(@PathVariable("log_workout_id") Long id) {

        LogWorkout logWorkout = service.findById(id);

        return new ResponseEntity<>(convertToLogWorkoutResponse(logWorkout), HttpStatus.OK);
    }

    @PostMapping("/{workout_set_id}")
    public ResponseEntity<HttpStatus> createLogWorkout(@PathVariable("workout_set_id") Long workoutSetId,
                                                       @RequestBody @Valid LogWorkoutRequest LogWorkout) {

        service.save(workoutSetId, convertToLogWorkout(LogWorkout));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/{log_workout_id}")
    public ResponseEntity<HttpStatus> updateLogWorkout(@PathVariable("log_workout_id") Long id,
                                                       @Valid @RequestBody LogWorkoutRequest logWorkoutRequest,
                                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //TODO
        }

        LogWorkout logWorkout = service.updateLogWorkout(id, convertToLogWorkout(logWorkoutRequest));

        return ResponseEntity.ok(HttpStatus.OK);
    }


    private LogWorkout convertToLogWorkout(LogWorkoutRequest logWorkoutRequest) {
        return model.map(logWorkoutRequest, LogWorkout.class);
    }

    private LogWorkoutResponse convertToLogWorkoutResponse(LogWorkout logWorkout) {
        return model.map(logWorkout, LogWorkoutResponse.class);
    }

}
