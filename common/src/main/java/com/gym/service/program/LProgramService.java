package com.gym.service.program;

import com.gym.domain.entity.LProgram;

import java.util.List;

public interface LProgramService {

    List<LProgram> findAll();

    LProgram findById(Long id);

    void save(Long parameterId, Long targetId, LProgram lProgram);

    LProgram createLProgram(LProgram lProgram);

    LProgram updateLProgram(Long id, LProgram lProgram);

    void deleteLProgram(Long id);

}
