package com.app.famousprogrammer.model;

import com.app.famousprogrammer.model.enums.Roles;
import com.app.famousprogrammer.model.enums.Skills;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@Table(name = "programmers")
public class Programmer extends User {

    public Programmer() {
    }

    public Programmer(Set<Team> teams, Set<Meeting> meetings, Set<Skills> skills) {
        this.teams = teams;
        this.meetings = meetings;
        this.skills = skills;
    }

    public Programmer(Long id, String name, String surname, LocalDate birthDate,
                      String email, String username,
                      String password, Boolean enabled,
                      Roles role) {
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "programmers_team",
            joinColumns = @JoinColumn(name = "programmer_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Team> teams;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "programmers_meeting",
            joinColumns = @JoinColumn(name = "programmer_id"),
            inverseJoinColumns = @JoinColumn(name = "meeting_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Meeting> meetings;

    @ElementCollection
    @CollectionTable(name = "skills",
            joinColumns = @JoinColumn(name = "programmer_id", referencedColumnName = "id"))
    @Column(name = "skills")
    @Enumerated(EnumType.STRING)
    private Set<Skills> skills;

}

