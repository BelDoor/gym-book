package com.gym.service.program;

import com.gym.domain.entity.LProgram;
import com.gym.domain.entity.parameter.ParameterGym;
import com.gym.domain.entity.TargetGym;
import com.gym.repository.LProgrameRepository;
import com.gym.service.parameter.ParameterGymService;
import com.gym.service.target.TargetGymService;
import com.gym.util.exception.custom.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LProgramServiceImpl implements LProgramService {

    private final LProgrameRepository repository;

    private final ParameterGymService parameter;

    private final TargetGymService target;


    @Override
    public List<LProgram> findAll() {
        List<LProgram> lPrograms = repository.findAll();
        return lPrograms;
    }

    @Override
    public LProgram findById(Long id) {
        Optional<LProgram> lProgram = repository.findById(id);
        return lProgram.orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void save(Long parameterId, Long targetId, LProgram lProgram) {
        ParameterGym parameterGym = parameter.findById(parameterId);
        TargetGym targetGym = target.findById(targetId);

        lProgram.setParameterGymId(parameterGym);
        lProgram.setTargetGymId(targetGym);
        lProgram.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        lProgram.setActively(true);

        repository.save(lProgram);
    }

    @Override
    public LProgram createLProgram(LProgram lProgram) {
        parameter.findById(lProgram.getParameterGymId().getParameterId());
        target.findById(lProgram.getTargetGymId().getTargetId());

        return repository.save(lProgram);
    }

    @Override
    public LProgram updateLProgram(Long id, LProgram lProgram) {
        LProgram programUpdate = findById(id);

        programUpdate.setDataStart(lProgram.getDataStart());
        programUpdate.setDataEnd(lProgram.getDataEnd());
        programUpdate.setGymProgram(lProgram.getGymProgram());

        programUpdate.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        return createLProgram(programUpdate);
    }

    @Override
    public void deleteLProgram(Long id) {

    }
}
