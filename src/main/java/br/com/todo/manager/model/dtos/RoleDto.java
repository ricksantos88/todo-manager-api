package br.com.todo.manager.model.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RoleDto {
    @JsonProperty("name")
    private String name;
}
