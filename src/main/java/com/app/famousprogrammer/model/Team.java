package com.app.famousprogrammer.model;

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
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private LocalDate creationDate;
    private Long teamLeaderId;

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "teams")
    private Set<Programmer> programmers;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "teams_meeting",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "meeting_id")
    )
    private Set<Meeting> meetings;
}
