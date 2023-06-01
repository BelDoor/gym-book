package com.gym.service.training;

import com.gym.domain.entity.LProgram;
import com.gym.domain.entity.TrainingBlock;
import com.gym.domain.entity.User;
import com.gym.domain.entity.parameter.ParameterGym;
import com.gym.repository.TrainingBlockRepository;
import com.gym.service.program.LProgramServiceImpl;
import com.gym.util.exception.custom.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainingBlockServiceImpl implements TrainingBlockService{

    private final TrainingBlockRepository repository;

    private final LProgramServiceImpl program;

    @Override
    public List<TrainingBlock> findAll() {
        List<TrainingBlock> trainingBlocks = repository.findAll();
        return trainingBlocks;
    }

    @Override
    public TrainingBlock findById(Long id) {
        Optional<TrainingBlock> trainingBlock = repository.findById(id);
        return trainingBlock.orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void save(Long programId, TrainingBlock trainingBlock) {

        LProgram lProgram = program.findById(programId);

        trainingBlock.setProgramId(lProgram);
        trainingBlock.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        repository.save(trainingBlock);
    }

    @Override
    public TrainingBlock createTrainingBlock(TrainingBlock trainingBlock) {
        return repository.save(trainingBlock);    }

    @Override
    public TrainingBlock updateTrainingBlock(Long id, TrainingBlock trainingBlock) {

        TrainingBlock training = findById(id);

        training.setNumWeek(trainingBlock.getNumWeek());
        training.setImportantPointsBlock(trainingBlock.getImportantPointsBlock());
        training.setActively(training.isActively());
        training.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        return createTrainingBlock(training);
    }

}
