package com.gym.service.parameter;

import com.gym.domain.entity.parameter.ParameterGym;
import com.gym.domain.entity.User;
import com.gym.domain.entity.parameter.ParameterGymUpdate;
import com.gym.repository.ParameterGymRepository;
import com.gym.service.user.UserService;
import com.gym.util.exception.custom.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParameterGymServiceImpl implements ParameterGymService {

    private final ParameterGymRepository repository;
    private final UserService userService;

    @Override
    public ParameterGym findById(Long id) {
        Optional<ParameterGym> parameterGym = repository.findById(id);
        return parameterGym.orElseThrow(UserNotFoundException::new); // TODO
    }

    @Override
    public List<ParameterGym> findAll() {
        List<ParameterGym> parameterGyms = repository.findAll();
        return parameterGyms;
    }

    @Override
    public void save(Long userId, ParameterGym parameterGym) {

        User user = userService.findById(userId);

        parameterGym.setUserId(user);
        parameterGym.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        repository.save(parameterGym);
    }


    @Override
    @Transactional
    public ParameterGym createParameterGym(ParameterGym parameterGym) {
        return repository.save(parameterGym);
    }

    @Override
    public void deleteParameter(Long id) {
        ParameterGym parameter = findById(id);
        repository.deleteParameter(id);
    }

    @Override
    @Transactional
    public ParameterGym updateParameterGym(Long id, ParameterGymUpdate parameterUpdate) {

        ParameterGym parameter = findById(id);

        parameter.setWeight(parameterUpdate.getWeight());
        parameter.setFatPercent(parameterUpdate.getFatPercent());
        parameter.setMaxBench(parameterUpdate.getMaxBench());
        parameter.setMaxSquat(parameterUpdate.getMaxSquat());
        parameter.setMaxTraction(parameterUpdate.getMaxTraction());
        parameter.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        return createParameterGym(parameter);
    }


}
