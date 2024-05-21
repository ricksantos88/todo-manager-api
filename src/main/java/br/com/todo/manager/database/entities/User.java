package br.com.todo.manager.database.entities;

import br.com.todo.manager.model.dtos.RoleDto;
import br.com.todo.manager.model.dtos.UserDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, length = 50)
    private String username;

    @Column(length = 50)
    private String secret;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role_id"})
    )
    private List<Role> roles;

    public User(UserDto userDto) {
        setUsername(userDto.username);
        setSecret(userDto.secret);
        setRoles(getRolesOfDtoList(userDto));
    }

    private static List<Role> getRolesOfDtoList(UserDto userDto) {
        return userDto.roles.stream().map(User::convertDtoToEntity).collect(Collectors.toList());
    }

    private static Role convertDtoToEntity(RoleDto roleDto) {
        return new Role(roleDto);
    }
}
