package br.com.todo.manager.database.entities;


import br.com.todo.manager.model.dtos.RoleDto;
import br.com.todo.manager.model.enums.RolePermissionsEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    @Enumerated(EnumType.STRING)
    private RolePermissionsEnum name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role(RoleDto roleDto) {
        setName(roleDto.getName());
    }
}
