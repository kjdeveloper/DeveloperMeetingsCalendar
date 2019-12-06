package com.app.famousprogrammer.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue
    private Long id;

  /*  private String name;
    private String surname;
    private LocalDate birthDate;
    private String email;
    private String username;
    private String password;
    private Boolean enabled;

    @Enumerated(EnumType.STRING)
    private Roles role;
*/

    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "client")
    private User user;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "clients_meetings",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "meeting_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Meeting> meetings;

}
