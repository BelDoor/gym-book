package com.gym.repository;

import com.gym.domain.entity.Role;
import com.gym.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "select * from roles where role_name = :roleName", nativeQuery = true)
    Optional<Role> findByRoleName(String roleName);

//    @Query(value = "select * from roles where user_id = :userId", nativeQuery = true)
//    Optional<Role> findByUserID(Long userId);

}
