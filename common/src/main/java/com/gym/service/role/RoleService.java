package com.gym.service.role;

import com.gym.domain.entity.Role;
import com.gym.domain.entity.User;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    Role findById(Long id);

    List<Role> findByRoleName(String Role);

    void save(Long userId, Role role);

    Role update(Long id, Role role);

    void deleteRole(Long id);


}
