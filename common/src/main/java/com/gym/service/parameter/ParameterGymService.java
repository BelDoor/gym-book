package com.gym.service.parameter;

import com.gym.domain.entity.parameter.ParameterGym;
import com.gym.domain.entity.parameter.ParameterGymUpdate;

import java.util.List;


public interface ParameterGymService {

    List<ParameterGym> findAll();

    ParameterGym findById(Long id);

    void save(Long userId, ParameterGym parameterGym);

    ParameterGym createParameterGym(ParameterGym parameterGym);

    ParameterGym updateParameterGym(Long id, ParameterGymUpdate parameterUpdate);

    void deleteParameter(Long id);
}
