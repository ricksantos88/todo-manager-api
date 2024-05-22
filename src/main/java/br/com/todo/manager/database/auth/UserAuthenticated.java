package br.com.todo.manager.database.auth;

import lombok.Data;

@Data
public class UserAuthenticated {
    private String username;
    private String password;
}
