package com.gym.service.workouts;

import com.gym.domain.entity.LProgram;
import com.gym.domain.entity.TrainingBlock;
import com.gym.domain.entity.Workouts;
import com.gym.repository.WorkoutsRepository;
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
public class WorkoutsServiceImpl implements WorkoutsService {

    private final WorkoutsRepository repository;

    private final TrainingBlockService training;

    @Override
    public List<Workouts> findAll() {
        List<Workouts> workouts = repository.findAll();
        return workouts;
    }

    @Override
    public Workouts findById(Long id) {
        Optional<Workouts> workouts = repository.findById(id);
        return workouts.orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void save(Long trainingId, Workouts workout) {

        TrainingBlock trainingBlock = training.findById(trainingId);

        workout.setTrainingBlockId(trainingBlock);
        workout.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        repository.save(workout);
    }

    @Override
    public Workouts createWorkouts(Workouts workout) {
        return repository.save(workout);
    }

    @Override
    public Workouts updateWorkouts(Long id, Workouts workout) {
        Workouts workoutUpdate = findById(id);

        workoutUpdate.setNumTraining(workout.getNumTraining());
        workoutUpdate.setTargetWorkout(workout.getTargetWorkout());
        workoutUpdate.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        return createWorkouts(workoutUpdate);
    }
}
