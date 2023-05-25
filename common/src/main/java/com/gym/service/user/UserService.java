package com.gym.service.user;

import com.gym.domain.entity.User;

import java.util.List;
import java.util.Optional;


public interface UserService {

    List<User> findAll();

    User findById(Long id);

    void save(User user);

    User update(Long id, User user);

    User findUserByPhoneNumber(Long phoneNumber);

    Optional<User> findEmail(String email);
}
