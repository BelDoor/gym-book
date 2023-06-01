package com.gym.controller.rest;

import com.gym.controller.dto.training.TrainingRequest;
import com.gym.controller.dto.workouts.WorkoutRequest;
import com.gym.domain.entity.TrainingBlock;
import com.gym.domain.entity.Workouts;
import com.gym.service.workouts.WorkoutsService;
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

@RestController
@RequestMapping("/rest/workout")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutsService service;

    private final ModelMapper model;


    @GetMapping
    public ResponseEntity<List<Workouts>> getAllWorkouts() {
        List<Workouts> targetsGym = service.findAll();
        return new ResponseEntity<>(targetsGym, HttpStatus.OK);
    }


    @GetMapping("/{workout_id}")
    public ResponseEntity<Workouts> getFindById(@PathVariable("workout_id") Long id) {

        Workouts workout = service.findById(id);

        return new ResponseEntity<>(workout, HttpStatus.OK);
    }

    @PostMapping("/{training_id}")
    public ResponseEntity<HttpStatus> createWorkout(@PathVariable("training_id") Long workoutId,
                                                      @RequestBody @Valid WorkoutRequest workout) {

        service.save(workoutId, convertToWorkouts(workout));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/{workout_id}")
    public ResponseEntity<HttpStatus> updateWorkout(@PathVariable("workout_id") Long id,
                                                       @Valid @RequestBody WorkoutRequest workoutRequest,
                                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //TODO
        }

        Workouts workout = service.updateWorkouts(id, convertToWorkouts(workoutRequest));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Workouts convertToWorkouts(WorkoutRequest workout) {
        return model.map(workout, Workouts.class);

    }
}
