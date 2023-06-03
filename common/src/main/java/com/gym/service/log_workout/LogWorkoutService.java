package com.gym.service.log_workout;

import com.gym.domain.entity.LogWorkout;
import com.gym.domain.entity.WorkoutSet;

import java.util.List;

public interface LogWorkoutService {
    List<LogWorkout> findAll();

    LogWorkout findById(Long id);

    void save(Long workoutSetId, LogWorkout logWorkout);

    LogWorkout createLogWorkout(LogWorkout logWorkout);

    LogWorkout updateLogWorkout(Long id, LogWorkout logWorkout);
}
