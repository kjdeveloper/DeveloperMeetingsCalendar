package com.app.famousprogrammer.model;

import com.app.famousprogrammer.model.enums.City;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "meetings")
public class Meeting {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private LocalDateTime startDate;
    private double duration;

    @Enumerated(EnumType.STRING)
    private City placeOfMeeting;

    @ManyToMany(mappedBy = "meetings")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<User> programmers = new HashSet<>();

    @ManyToMany(mappedBy = "meetings")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Team> teams = new HashSet<>();

}

