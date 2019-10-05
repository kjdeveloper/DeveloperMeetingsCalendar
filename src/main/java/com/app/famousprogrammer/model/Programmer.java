package com.app.famousprogrammer.model;

import com.app.famousprogrammer.model.enums.Skills;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "programmers")
public class Programmer {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String surname;
    private String username;
    private LocalDate birthDate;
    private String email;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "programmers_meeting",
            joinColumns = @JoinColumn(name = "programmer_id"),
            inverseJoinColumns = @JoinColumn(name = "meeting_id")
    )
    private Set<Meeting> meetings;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "programmers_team",
            joinColumns = @JoinColumn(name = "programmer_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<Team> teams;

    @ElementCollection
    @CollectionTable(name = "skills",
            joinColumns = @JoinColumn(name = "programmer_id", referencedColumnName = "id"))
    @Column(name = "skills")
    @Enumerated(EnumType.STRING)
    private Set<Skills> skills;

}
