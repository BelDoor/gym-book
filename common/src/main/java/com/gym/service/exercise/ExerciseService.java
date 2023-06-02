package com.gym.service.exercise;

import com.gym.domain.entity.CExercise;
import com.gym.domain.entity.CMuscle;

import java.util.List;

public interface ExerciseService {
    List<CExercise> findAll();

    CExercise findById(Long id);

    void save(CExercise cExercise);

    CExercise updateCExercise(Long id, CExercise cExercise);
}
