package br.com.todo.manager.modules.user.service;

import br.com.todo.manager.database.entities.User;
import br.com.todo.manager.model.dtos.UserDto;
import br.com.todo.manager.model.dtos.UserResponseDto;
import br.com.todo.manager.modules.user.provider.UserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserProvider userProvider;


    public UserResponseDto createUser(UserDto userDto) {
        User user = new User(userDto);
        return new UserResponseDto(userProvider.createUser(user));
    }

}
