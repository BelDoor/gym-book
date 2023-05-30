package com.gym.service.target;

import com.gym.domain.entity.TargetGym;

import java.util.List;

public interface TargetGymService {

    List<TargetGym> findAll();

    TargetGym findById(Long id);

    void save(Long userId, TargetGym targetGym);

    TargetGym createTargetGym(TargetGym targetGym);

    TargetGym updateTargetGym(Long id, TargetGym targetGym);

    void deleteTarget(Long id);

}
