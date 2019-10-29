package com.app.famousprogrammer.model;

import com.app.famousprogrammer.model.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Inheritance
@Table(name = "users")
public abstract class User {

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

    @Enumerated(EnumType.STRING)
    private Roles role;

}
