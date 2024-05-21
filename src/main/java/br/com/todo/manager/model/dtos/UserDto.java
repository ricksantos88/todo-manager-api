package br.com.todo.manager.model.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    @JsonProperty("username")
    public String username;

    @JsonProperty("secret")
    public String secret;

    @JsonProperty("roles")
    public List<RoleDto> roles;

}
