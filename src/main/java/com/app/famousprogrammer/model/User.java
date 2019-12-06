package com.app.famousprogrammer.model;

import com.app.famousprogrammer.model.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String surname;
    private LocalDate birthDate;
    private String email;
    private String username;
    private String password;
    private Boolean enabled;
    private String filename;

    @ElementCollection
    @CollectionTable(
            name = "roles",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Set<Roles> role;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "programmer_id", unique = true)
    private Programmer programmer;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id")
    private Client client;
}
