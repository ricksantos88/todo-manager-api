package br.com.todo.manager.database.repositories;

import br.com.todo.manager.database.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}
