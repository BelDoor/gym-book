package com.gym.controller.rest;

import com.gym.controller.dto.exercise.ExerciseRequest;
import com.gym.controller.dto.muscle.MuscleRequest;
import com.gym.domain.entity.CExercise;
import com.gym.domain.entity.CMuscle;
import com.gym.service.exercise.ExerciseService;
import com.gym.service.muscle.MuscleService;
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
@RequestMapping("/rest/exercise")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService service;

    private final ModelMapper model;


    @GetMapping
    public ResponseEntity<List<CExercise>> getAllExercise() {
        List<CExercise> cExercises = service.findAll();
        return new ResponseEntity<>(cExercises, HttpStatus.OK);
    }

    @GetMapping("/{exercise_id}")
    public ResponseEntity<CExercise> getFindById(@PathVariable("exercise_id") Long id) {

        CExercise cExercise = service.findById(id);

        return new ResponseEntity<>(cExercise, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createExercise(@RequestBody @Valid ExerciseRequest exercise) {

        service.save(convertToExercise(exercise));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateExercise(@PathVariable Long id,
                                                   @Valid @RequestBody ExerciseRequest exercise,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //TODO
        }

        CExercise cExercise = service.updateCExercise(id, convertToExercise(exercise));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private CExercise convertToExercise(ExerciseRequest exercise) {
        return model.map(exercise, CExercise.class);
    }

}
