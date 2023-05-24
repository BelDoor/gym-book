package com.gym.service.role;

import com.gym.domain.entity.Role;
import com.gym.domain.entity.User;
import com.gym.repository.RoleRepository;
import com.gym.service.user.UserService;
import com.gym.util.exception.custom.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImp implements RoleService{

    private final RoleRepository repository;
    private final UserService userService;

    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }

    @Override
    public Role findById(Long id) {
        Optional<Role> role = repository.findById(id);
        return role.orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<Role> findByRoleName(String roleName) {
        Optional<Role> role = repository.findByRoleName(roleName);
        return (List<Role>) role.orElseThrow(UserNotFoundException::new); //!!!creat exception
    }

    @Override
    public void save(Long userId, Role role) {

        User user = userService.findById(userId);

        role.setUser(user);
        repository.save(role);
    }

    @Override
    public Role update(Long id, Role role) {

        Role findRole = findById(id);
        findRole = role;

        return repository.save(findRole);
    }

    @Override
    public void deleteRole(Long id) {
        repository.delete(findById(id));
    }
}
