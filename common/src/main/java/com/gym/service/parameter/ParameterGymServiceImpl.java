package com.gym.service.parameter;

import com.gym.domain.entity.ParameterGym;
import com.gym.domain.entity.User;
import com.gym.repository.ParameterGymRepository;
import com.gym.service.user.UserService;
import com.gym.util.exception.custom.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return parameterGym.orElseThrow(UserNotFoundException::new);
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
    public ParameterGym updateParameterGym(Long id, ParameterGym parameterGym) {

        ParameterGym parameterUpdate = findById(id);
        parameterUpdate = parameterGym;
        return createParameterGym(parameterUpdate);
    }
}
