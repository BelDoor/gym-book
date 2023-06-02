package com.gym.service.exercise;

import com.gym.domain.entity.CExercise;
import com.gym.domain.entity.CMuscle;
import com.gym.repository.CExerciseRepository;
import com.gym.repository.CMuscleRepository;
import com.gym.util.exception.custom.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService{

    private final CExerciseRepository repository;

    @Override
    public List<CExercise> findAll() {
        List<CExercise> cExercises = repository.findAll();
        return cExercises;
    }

    @Override
    public CExercise findById(Long id) {
        Optional<CExercise> cExercise = repository.findById(id);
        return cExercise.orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void save(CExercise cExercise) {
        repository.save(cExercise);
    }

    @Override
    public CExercise updateCExercise(Long id, CExercise cExercise) {
        CExercise updateCExercise = findById(id);

        updateCExercise.setExerciseName(cExercise.getExerciseName());
        updateCExercise.setExerciseTXT(cExercise.getExerciseTXT());

        return repository.save(updateCExercise);
    }
}
