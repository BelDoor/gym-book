package com.gym.service.muscle;

import com.gym.domain.entity.CMuscle;

import java.util.List;

public interface MuscleService {
    List<CMuscle> findAll();

    CMuscle findById(Long id);

    void save(CMuscle cMuscle);

    CMuscle updateCMuscle(Long id, CMuscle cMuscle);
}
