package com.gym.service.workouts;


import com.gym.domain.entity.TrainingBlock;
import com.gym.domain.entity.Workouts;

import java.util.List;

public interface WorkoutsService {

    List<Workouts> findAll();

    Workouts findById(Long id);

    void save(Long trainingId, Workouts workout);

    Workouts createWorkouts(Workouts workout);

    Workouts updateWorkouts(Long id, Workouts workout);

}
