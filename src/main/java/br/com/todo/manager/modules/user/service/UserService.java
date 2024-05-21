package br.com.todo.manager.modules.user.service;

import br.com.todo.manager.database.entities.User;
import br.com.todo.manager.model.dtos.UserDto;
import br.com.todo.manager.model.dtos.UserResponseDto;
import br.com.todo.manager.modules.user.provider.UserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserProvider userProvider;

    public UserResponseDto createUser(UserDto userDto) {
        User user = new User(userDto);
        return new UserResponseDto(userProvider.createUser(user));
    }

    public UserResponseDto updateUser(UUID userId, UserDto userDto) throws Exception {
        User userSaved = getUserById(userId);
        userSaved.setUsername(userDto.getUsername());
        userSaved.setSecret(userDto.getSecret());
        return new UserResponseDto(userProvider.updateUser(userSaved));
    }

    public void deleteUser(UUID userId) throws Exception {
        userProvider.deleteUser(getUserById(userId));
    }

    private User getUserById(UUID userId) throws Exception {
        return userProvider.findUserById(userId);
    }

}
