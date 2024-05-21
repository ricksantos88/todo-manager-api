package br.com.todo.manager.modules.user.controller;

import br.com.todo.manager.model.dtos.UserDto;
import br.com.todo.manager.model.dtos.UserResponseDto;
import br.com.todo.manager.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {

    public static final String BASE_URL = "/users";
    public static final String POST_CREATE_USER = BASE_URL;
    public static final String FIND_USER_BY_ID = BASE_URL + "/{userId}";
    public static final String PUT_UPDATE_USER_BY_ID = BASE_URL + "/{userId}";
    public static final String DELETE_USER_BY_ID = BASE_URL + "/{userId}";

    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(POST_CREATE_USER)
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(PUT_UPDATE_USER_BY_ID)
    public UserResponseDto updateBookById(@PathVariable UUID userId, @RequestBody UserDto userDto) throws Exception {
        return userService.updateUser(userId, userDto);
    }

    @DeleteMapping(DELETE_USER_BY_ID)
    public void updateBookById(@PathVariable UUID userId) throws Exception {
        userService.deleteUser(userId);
    }

}
