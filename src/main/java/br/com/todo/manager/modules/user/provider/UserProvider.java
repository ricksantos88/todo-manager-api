package br.com.todo.manager.modules.user.provider;

import br.com.todo.manager.database.entities.User;
import br.com.todo.manager.database.repositories.UserRepository;
import br.com.todo.manager.model.exceptions.NotFoundException;
import br.com.todo.manager.model.exceptions.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserProvider {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return saveUser(user);
    }

    public User updateUser(User user) {
        return saveUser(user);
    }

    public User findUserById(UUID userId) throws Exception {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user.not.found", HttpStatus.NOT_FOUND));
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user.not.found", HttpStatus.NOT_FOUND));
    }

    private User saveUser(User user) {
        return userRepository.save(user);
    }
}
