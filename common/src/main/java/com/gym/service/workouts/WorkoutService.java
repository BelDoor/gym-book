package com.gym.service.workouts;


import com.gym.domain.entity.Workout;

import java.util.List;

public interface WorkoutService {

    List<Workout> findAll();

    Workout findById(Long id);

    void save(Long trainingId, Workout workout);

    Workout createWorkouts(Workout workout);

    Workout updateWorkouts(Long id, Workout workout);

}
