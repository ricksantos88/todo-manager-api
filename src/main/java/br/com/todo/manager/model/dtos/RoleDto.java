package br.com.todo.manager.model.dtos;

import br.com.todo.manager.model.enums.RolePermissionsEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RoleDto {
    @JsonProperty("name")
    private RolePermissionsEnum name;
}
