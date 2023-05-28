package com.gym.repository;

import com.gym.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from users where user_phone_number = :phoneNumber", nativeQuery = true)
    Optional<User> findUserByPhoneNumber(Long phoneNumber);

    @Query(value = "select * from users where user_email = :email", nativeQuery = true)
    Optional<User> findUserByEmail(String email);
}
