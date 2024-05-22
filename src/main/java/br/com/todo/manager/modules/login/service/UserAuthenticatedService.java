package br.com.todo.manager.modules.login.service;

import br.com.todo.manager.database.entities.User;
import br.com.todo.manager.modules.user.provider.UserProvider;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserAuthenticatedService implements UserDetailsService {

    @Autowired
    private UserProvider userProvider;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userProvider.findUserByUsername(username);

        List<SimpleGrantedAuthority> roles = user.getRoles()
            .stream()
            .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
            .toList();

        String loggedUsername = user.getUsername();
        String loggedSecret = user.getSecret();
        return new org.springframework.security.core.userdetails.User(loggedUsername, loggedSecret, roles);
    }



}
