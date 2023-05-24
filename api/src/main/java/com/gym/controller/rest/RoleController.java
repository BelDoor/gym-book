package com.gym.controller.rest;

import com.gym.controller.dto.RoleDTO;
import com.gym.domain.entity.Role;
import com.gym.service.role.RoleService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/rest/role")
@AllArgsConstructor
public class RoleController {

    private final RoleService service;
    private final ModelMapper model;

    @GetMapping
    public ResponseEntity<List<Role>> getAllRole() {

        List<Role> role = service.findAll();

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @GetMapping("/{role_id}")
    public ResponseEntity<RoleDTO> getFindById(@PathVariable("role_id") Long id) {

        Role role = service.findById(id);

        return new ResponseEntity<>(convertToRoleDto(role), HttpStatus.OK);
    }

    @GetMapping("/{role_name}")
    public ResponseEntity<List<RoleDTO>> getFindById(@PathVariable("role_name") String name) {

        List<RoleDTO> role = service.findByRoleName(name).stream()
                .map(this::convertToRoleDto).collect(Collectors.toList());

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping("/{user_id}")
    public ResponseEntity<HttpStatus> createRole(@PathVariable("user_id") Long userId, @RequestBody @Valid Role role) {

        service.save(userId, role);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRole(@PathVariable("id") Long id) {

        service.deleteRole(id);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    private Role convertToRole(RoleDTO roleDTO) {
        return model.map(roleDTO, Role.class);
    }

    private RoleDTO convertToRoleDto(Role role) {
        return model.map(role, RoleDTO.class);
    }

}
