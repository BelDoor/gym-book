package com.gym.service.training;

import com.gym.domain.entity.LProgram;
import com.gym.domain.entity.TrainingBlock;

import java.util.List;

public interface TrainingBlockService {

    List<TrainingBlock> findAll();

    TrainingBlock findById(Long id);

    void save(Long programId, TrainingBlock trainingBlock);

    TrainingBlock createTrainingBlock(TrainingBlock trainingBlock);

    TrainingBlock updateTrainingBlock(Long id, TrainingBlock trainingBlock);
}
