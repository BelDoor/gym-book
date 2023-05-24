package com.gym.service.parameter;

import com.gym.domain.entity.ParameterGym;
import com.gym.domain.entity.Role;

import java.util.List;


public interface ParameterGymService {

    List<ParameterGym> findAll();

    ParameterGym findById(Long id);

    void save(Long userId, ParameterGym parameterGym);

    ParameterGym createParameterGym(ParameterGym parameterGym);

    ParameterGym updateParameterGym(Long id, ParameterGym parameterGym);

    void deleteParameter(Long id);
}
