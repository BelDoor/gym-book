package com.gym.controller.rest;

import com.gym.controller.dto.user.UserDTO;
import com.gym.controller.dto.user.UserRequest;
import com.gym.domain.column.Gender;
import com.gym.domain.entity.User;
import com.gym.service.user.UserService;
import com.gym.util.exception.custom.UserNotFoundException;
import com.gym.util.exception.response.UserError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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


    @Operation(
            summary = "User Find All Search",
            description = "Find All Users without limitations",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded UsersDTO",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserDTO.class)))

                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.findAll().stream()
                .map(this::convertToUserDto).collect(Collectors.toList());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(
            summary = "Search user by id",
            description = "Find one User without by id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded UsersDTO",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserDTO.class)))
                    )
            }
    )
    @GetMapping("/{user_id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("user_id") Long id) {

        User user = userService.findById(id);

        return new ResponseEntity<>(convertToUserDto(user), HttpStatus.OK);
    }

    @Operation(
            summary = "Save user",
            description = "Save a new User ",
            parameters = {
                    @Parameter(name = "firstName",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Helga", type = "string", description = "user first name")),
                    @Parameter(name = "surname",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Paklit", type = "string", description = "user surname")
                    ),
                    @Parameter(name = "birthday",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "857026419000", type = "timestamp", description = "user birth date")
                    ),
                    @Parameter(name = "height",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "185", type = "Integer", description = "user height")
                    ),
                    @Parameter(name = "userPassword",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(example = "QWERty23", type = "String", description = "User password")),
                    @Parameter(name = "userEmail",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(example = "Bonjo2021@gmail.com",
                                    type = "String", description = "User email")),
                    @Parameter(name = "userPhone",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(example = "375238297231", type = "Long", description = "User phone")),
                    @Parameter(name = "gender",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "NOT_SELECTED", type = "Gender",
                                    implementation = Gender.class, description = "user gender"))
            },
            responses = {
                    @ApiResponse(
                            responseCode = "CREATED",
                            description = "User created",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserRequest.class)))
                    ),
                    @ApiResponse(
                            responseCode = "BAD_REQUEST",
                            description = "Validation error"
                    )
            }
    )
    @PostMapping
    public ResponseEntity<HttpStatus> save(
            @Parameter(hidden = true)
            @RequestBody @Valid UserRequest userRequest,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            //TODO
        }

        userService.save(convertToUser(userRequest));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }


    @Operation(
            summary = "Update user",
            description = "Update a User ",
            parameters = {
                    @Parameter(name = "firstName",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Helga", type = "string", description = "user first name")),
                    @Parameter(name = "surname",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Paklit", type = "string", description = "user surname")
                    ),
                    @Parameter(name = "birthday",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "857026419000", type = "timestamp", description = "user birth date")
                    ),
                    @Parameter(name = "height",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "185", type = "Integer", description = "user height")
                    ),
                    @Parameter(name = "userPassword",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(example = "QWERty23", type = "String", description = "User password")),
                    @Parameter(name = "userEmail",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(example = "Bonjo2021@gmail.com",
                                    type = "String", description = "User email")),
                    @Parameter(name = "userPhone",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(example = "375238297231", type = "Long", description = "User phone")),
                    @Parameter(name = "gender",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "NOT_SELECTED", type = "Gender",
                                    implementation = Gender.class, description = "user gender"))
            },
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "User update",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserRequest.class)))
                    ),
                    @ApiResponse(
                            responseCode = "BAD_REQUEST",
                            description = "Validation error"
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @Parameter(hidden = true)
            @PathVariable Long id,
            @Valid @RequestBody UserRequest userRequest,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //TODO
        }

        User updatedUser = userService.update(id, convertToUser(userRequest));

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @Operation(
            summary = "Search search by phone number",
            description = "Find one User without by id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded UsersDTO",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserDTO.class)))
                    )
            }
    )
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
