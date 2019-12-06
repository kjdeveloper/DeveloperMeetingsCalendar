package com.app.famousprogrammer.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private LocalDate creationDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "programmer_id")
    private Programmer teamLeader;

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "teams")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Programmer> programmers;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "teams_meetings",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "meeting_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Meeting> meetings;
}
