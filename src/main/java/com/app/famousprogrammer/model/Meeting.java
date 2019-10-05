package com.app.famousprogrammer.model;


import com.app.famousprogrammer.model.enums.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String topic;
    private String description;
    private LocalDateTime startDate;
    private int duration;

    @ElementCollection
    @CollectionTable(name = "city",
            joinColumns = @JoinColumn(name = "meeting_id", referencedColumnName = "id"))
    private City placeOfMeeting;

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "meetings")
    private Set<Programmer> programmers = new HashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "meetings")
    private Set<User> users = new HashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "meetings")
    private Set<Team> teams = new HashSet<>();

}
