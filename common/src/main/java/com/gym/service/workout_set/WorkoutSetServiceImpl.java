package com.gym.service.workout_set;

import com.gym.domain.entity.CExercise;
import com.gym.domain.entity.Workout;
import com.gym.domain.entity.WorkoutSet;
import com.gym.repository.WorkoutSetRepository;
import com.gym.service.exercise.ExerciseService;
import com.gym.service.workouts.WorkoutService;
import com.gym.util.exception.custom.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkoutSetServiceImpl implements WorkoutSetService {

    private final WorkoutSetRepository repository;

    private final WorkoutService workoutService;

    private final ExerciseService exerciseService;

    @Override
    public List<WorkoutSet> findAll() {
        List<WorkoutSet> workoutSet = repository.findAll();
        return workoutSet;
    }

    @Override
    public WorkoutSet findById(Long id) {
        Optional<WorkoutSet> workoutSet = repository.findById(id);
        return workoutSet.orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void save(Long workoutId, Long exerciseId, WorkoutSet workoutSet) {

        Workout workout = workoutService.findById(workoutId);
        CExercise exercise = exerciseService.findById(exerciseId);

        if(workoutSet.getMinWeight() > workoutSet.getMaxWeight() ||
                workoutSet.getMinRepetitions() > workoutSet.getMaxRepetitions()){
            throw new UserNotFoundException();
        }


        workoutSet.setWorkout(workout);
        workoutSet.setExercise(exercise);
        workoutSet.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        repository.save(workoutSet);
    }

    @Override
    public WorkoutSet createWorkoutSet(WorkoutSet workoutSet) {
        return repository.save(workoutSet);
    }

    @Override
    public WorkoutSet updateWorkoutSet(Long id, WorkoutSet workoutSet) {
        WorkoutSet workoutUpdate = findById(id);

        workoutUpdate.setQuantitySet(workoutSet.getQuantitySet());

        if (workoutSet.getMaxWeight() > workoutSet.getMinWeight() &&
                workoutSet.getMinRepetitions() < workoutSet.getMaxRepetitions()) {

            workoutUpdate.setMaxWeight(workoutSet.getMaxWeight());
            workoutUpdate.setMinWeight(workoutSet.getMinWeight());

            workoutUpdate.setMaxRepetitions(workoutSet.getMaxRepetitions());
            workoutUpdate.setMinRepetitions(workoutSet.getMinRepetitions());

        } else {
            throw new UserNotFoundException();
        }

        workoutUpdate.setRestTime(workoutSet.getRestTime());
        workoutUpdate.setMaxRepetitions(workoutSet.getMaxRepetitions());
        workoutUpdate.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        return createWorkoutSet(workoutUpdate);
    }
}
