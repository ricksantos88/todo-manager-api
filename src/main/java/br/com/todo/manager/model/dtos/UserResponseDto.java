package br.com.todo.manager.model.dtos;

import br.com.todo.manager.database.entities.Role;
import br.com.todo.manager.database.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserResponseDto {
    @JsonProperty("username")
    public String username;

    @JsonProperty("roles")
    public List<RoleDto> roles;
    public UserResponseDto(User user) {
        setUsername(user.getUsername());
        setRoles(getRolesOfDtoList(user));
    }

    private static List<RoleDto> getRolesOfDtoList(User user) {
        return user.getRoles().stream().map(UserResponseDto::convertEntityToDto).collect(Collectors.toList());
    }

    private static RoleDto convertEntityToDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setName(role.getName());
        return roleDto;
    }
}
