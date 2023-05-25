package com.gym.service.user;

import com.gym.domain.entity.User;
import com.gym.repository.UserRepository;
import com.gym.util.exception.custom.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(UserNotFoundException::new);
    }

    @Override
    @Transactional
    public void save(User user) {

        enrichUser(user);

        userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(Long id, User user) {

        User userFind = findById(id);

        user.setUserId(id);
        changeUser(user);
        user.setCreated(userFind.getCreated());
        return userRepository.save(user);
    }

    @Override
    public User findUserByPhoneNumber(Long phoneNumber) {
        return userRepository.findUserByPhoneNumber(phoneNumber).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Optional<User> findEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    private void enrichUser(User user) {

        user.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        user.setIsActively(true);

    }

    private void changeUser(User user) {

        user.setChanged(Timestamp.valueOf(LocalDateTime.now()));
        user.setIsActively(true);

    }


}
