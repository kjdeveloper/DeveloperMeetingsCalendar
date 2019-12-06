package com.app.famousprogrammer.model;

import com.app.famousprogrammer.model.enums.Skills;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "programmers")
public class Programmer {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "programmer")
    private User user;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "programmers_teams",
            joinColumns = @JoinColumn(name = "programmer_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Team> teams;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "programmers_meetings",
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

