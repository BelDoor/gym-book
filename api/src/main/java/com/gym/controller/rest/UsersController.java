package com.gym.controller.rest;

import com.gym.controller.dto.user.UserDTO;
import com.gym.controller.dto.user.UserRequest;
import com.gym.domain.entity.User;
import com.gym.service.user.UserService;
import com.gym.util.exception.custom.UserNotFoundException;
import com.gym.util.exception.response.UserError;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;
    private final ModelMapper modelMapper;


    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.findAll().stream()
                .map(this::convertToUserDto).collect(Collectors.toList());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("user_id") Long id) {

        User user = userService.findById(id);

        return new ResponseEntity<>(convertToUserDto(user), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid UserRequest userRequest,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            //TODO
        }

        userService.save(convertToUser(userRequest));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,
                                           @Valid @RequestBody UserRequest userRequest,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //TODO
        }

        User updatedUser = userService.update(id, convertToUser(userRequest));

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping("/search-by-phone-number/{phoneNumber}")
    public ResponseEntity<UserDTO> findUserByPhoneNumber(@PathVariable Long phoneNumber) {

        User user = userService.findUserByPhoneNumber(phoneNumber);

        return new ResponseEntity<>(convertToUserDto(user), HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<UserError> handlerException(UserNotFoundException e) {
        UserError userError = new UserError(
                "User wiht this id wasn't found!",
                System.currentTimeMillis());
        return new ResponseEntity<>(userError, HttpStatus.NOT_FOUND);
    }

    private UserDTO convertToUserDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private User convertToUser(UserRequest userRequest) {
        return modelMapper.map(userRequest, User.class);
    }


}
