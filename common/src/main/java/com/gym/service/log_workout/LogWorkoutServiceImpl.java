package com.gym.service.log_workout;

import com.gym.domain.entity.LogWorkout;
import com.gym.domain.entity.TrainingBlock;
import com.gym.domain.entity.Workout;
import com.gym.domain.entity.WorkoutSet;
import com.gym.repository.LogWorkoutRepository;
import com.gym.service.workout_set.WorkoutSetService;
import com.gym.util.exception.custom.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogWorkoutServiceImpl implements LogWorkoutService {

    private final LogWorkoutRepository repository;

    private final WorkoutSetService workoutSet;

    @Override
    public List<LogWorkout> findAll() {
        List<LogWorkout> logWorkout = repository.findAll();
        return logWorkout;
    }

    @Override
    public LogWorkout findById(Long id) {
        Optional<LogWorkout> logWorkouts = repository.findById(id);
        return logWorkouts.orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void save(Long workoutSetId, LogWorkout logWorkout) {
        WorkoutSet workout = workoutSet.findById(workoutSetId);

        logWorkout.setWorkoutSet(workout);
        logWorkout.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        repository.save(logWorkout);
    }

    @Override
    public LogWorkout createLogWorkout(LogWorkout logWorkout) {
        return repository.save(logWorkout);
    }

    @Override
    public LogWorkout updateLogWorkout(Long id, LogWorkout logWorkout) {
        LogWorkout logWorkoutUpdate = findById(id);

        logWorkoutUpdate.setLogWeight(logWorkout.getLogWeight());
        logWorkoutUpdate.setLogRepetitions(logWorkout.getLogRepetitions());
        logWorkoutUpdate.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        return createLogWorkout(logWorkoutUpdate);
    }
}
