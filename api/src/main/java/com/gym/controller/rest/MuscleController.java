package com.gym.controller.rest;

import com.gym.controller.dto.muscle.MuscleRequest;
import com.gym.domain.entity.CMuscle;
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
@RequestMapping("/rest/muscle")
@RequiredArgsConstructor
public class MuscleController {

    private final MuscleService service;

    private final ModelMapper model;

    @GetMapping
    public ResponseEntity<List<CMuscle>> getAllPMuscle() {
        List<CMuscle> cMuscles = service.findAll();
        return new ResponseEntity<>(cMuscles, HttpStatus.OK);
    }

    @GetMapping("/{muscle_id}")
    public ResponseEntity<CMuscle> getFindById(@PathVariable("muscle_id") Long id) {

        CMuscle cMuscle = service.findById(id);

        return new ResponseEntity<>(cMuscle, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createParameter(@RequestBody @Valid MuscleRequest muscle) {

        service.save(convertToMuscle(muscle));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateMuscle(@PathVariable Long id,
                                                       @Valid @RequestBody MuscleRequest muscle,
                                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //TODO
        }

        CMuscle cMuscle = service.updateCMuscle(id, convertToMuscle(muscle));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private CMuscle convertToMuscle(MuscleRequest muscle) {
        return model.map(muscle, CMuscle.class);
    }

}
