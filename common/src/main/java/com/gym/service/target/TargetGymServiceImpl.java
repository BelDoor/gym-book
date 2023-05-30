package com.gym.service.target;

import com.gym.domain.entity.ParameterGym;
import com.gym.domain.entity.TargetGym;
import com.gym.domain.entity.User;
import com.gym.repository.TargetGymRepository;
import com.gym.service.user.UserService;
import com.gym.util.exception.custom.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TargetGymServiceImpl implements TargetGymService{

    private final TargetGymRepository repository;
    private final UserService userService;

    @Override
    public List<TargetGym> findAll() {
        List<TargetGym> targetGym = repository.findAll();
        return targetGym;
    }

    @Override
    public TargetGym findById(Long id) {
        Optional<TargetGym> targetGym = repository.findById(id);
        return targetGym.orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void save(Long userId, TargetGym targetGym) {

        User user = userService.findById(userId);

        targetGym.setUserId(user);
        repository.save(targetGym);
    }

    @Override
    public TargetGym createTargetGym(TargetGym targetGym) {
        return repository.save(targetGym);
    }

    @Override
    public TargetGym updateTargetGym(Long id, TargetGym targetGym) {

        TargetGym targetGymUpdate = findById(id);
        targetGymUpdate = targetGym;
        return createTargetGym(targetGymUpdate);
    }

    @Override
    public void deleteTarget(Long id) {

        TargetGym targetGym = findById(id);
        repository.deleteTargetGym(id);
    }
}
