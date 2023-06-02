package com.gym.service.workouts;

import com.gym.domain.entity.TrainingBlock;
import com.gym.domain.entity.Workout;
import com.gym.repository.WorkoutRepository;
import com.gym.service.training.TrainingBlockService;
import com.gym.util.exception.custom.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkoutServiceImpl implements WorkoutService {

    private final WorkoutRepository repository;

    private final TrainingBlockService training;

    @Override
    public List<Workout> findAll() {
        List<Workout> workouts = repository.findAll();
        return workouts;
    }

    @Override
    public Workout findById(Long id) {
        Optional<Workout> workouts = repository.findById(id);
        return workouts.orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void save(Long trainingId, Workout workout) {

        TrainingBlock trainingBlock = training.findById(trainingId);

        workout.setTrainingBlockId(trainingBlock);
        workout.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        repository.save(workout);
    }

    @Override
    public Workout createWorkouts(Workout workout) {
        return repository.save(workout);
    }

    @Override
    public Workout updateWorkouts(Long id, Workout workout) {
        Workout workoutUpdate = findById(id);

        workoutUpdate.setNumTraining(workout.getNumTraining());
        workoutUpdate.setTargetWorkout(workout.getTargetWorkout());
        workoutUpdate.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        return createWorkouts(workoutUpdate);
    }
}
