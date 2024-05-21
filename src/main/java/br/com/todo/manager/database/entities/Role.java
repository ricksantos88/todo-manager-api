package br.com.todo.manager.database.entities;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "roles")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, length = 20)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
