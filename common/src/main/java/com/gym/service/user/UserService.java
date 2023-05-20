package com.gym.service.user;

import com.gym.domain.entity.User;

import java.util.List;
import java.util.Optional;


public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User create(User user);

    void save(User user);
}
