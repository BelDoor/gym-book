package com.gym.controller.rest;


import com.gym.controller.rest.requests.UserCreateRequest;
import com.gym.domain.entity.User;
import com.gym.service.user.UserService;
import com.gym.util.exception.custom.UserNotFoundException;
import com.gym.util.exception.response.UserError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<User> getUser(@PathVariable("user_id") Long id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid UserCreateRequest userCreateRequest,
                                           BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            //TODO
        }

        User user = User.builder()
                .firstName(userCreateRequest.getFirstName())
                .surname(userCreateRequest.getSurname())
                .gender(userCreateRequest.getGender())
                .birthday(userCreateRequest.getBirthday())
                .height(userCreateRequest.getHeight())
                .userPassword(userCreateRequest.getUserPassword())
                .userEmail(userCreateRequest.getUserEmail())
                .userPhone(userCreateRequest.getUserPhone())
                .created(Timestamp.valueOf(LocalDateTime.now()))
                .isActively(true)
                .build();

        userService.save(user);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }


    @ExceptionHandler
    private ResponseEntity<UserError> handlerException(UserNotFoundException e) {
        UserError userError = new UserError(
                "User wiht this id wasn't found!",
                System.currentTimeMillis());
        return new ResponseEntity<>(userError, HttpStatus.NOT_FOUND);
    }


}
