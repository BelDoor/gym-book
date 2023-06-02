package com.gym.service.workout_set;

import com.gym.domain.entity.Workout;
import com.gym.domain.entity.WorkoutSet;

import java.util.List;

public interface WorkoutSetService {

    List<WorkoutSet> findAll();

    WorkoutSet findById(Long id);

    void save(Long workoutId, Long exerciseId, WorkoutSet workoutSet);

    WorkoutSet createWorkoutSet(WorkoutSet workoutSet);

    WorkoutSet updateWorkoutSet(Long id, WorkoutSet workoutSet);
}
