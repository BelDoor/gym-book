package com.gym.service.muscle;

import com.gym.domain.entity.CMuscle;
import com.gym.repository.CMuscleRepository;
import com.gym.util.exception.custom.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MuscleServiceImpl implements MuscleService{

    private final CMuscleRepository repository;

    @Override
    public List<CMuscle> findAll() {
        List<CMuscle> cMuscle = repository.findAll();
        return cMuscle;
    }

    @Override
    public CMuscle findById(Long id) {
        Optional<CMuscle> cMuscle = repository.findById(id);
        return cMuscle.orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void save(CMuscle cMuscle) {
        repository.save(cMuscle);
    }

    @Override
    public CMuscle updateCMuscle(Long id, CMuscle cMuscle) {
        CMuscle updateCMuscle = findById(id);

        updateCMuscle.setMuscleName(cMuscle.getMuscleName());
        updateCMuscle.setMuscleTXT(cMuscle.getMuscleTXT());

        return repository.save(updateCMuscle);
    }
}
