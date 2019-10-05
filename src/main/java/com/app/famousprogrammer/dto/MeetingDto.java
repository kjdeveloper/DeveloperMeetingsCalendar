package com.app.famousprogrammer.dto;

import com.app.famousprogrammer.model.enums.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeetingDto {

    private Long id;
    private String topic;
    private String description;
    private LocalDateTime startDate;
    private int duration;
    private City placeOfMeeting;
    private Set<ProgrammerDto> programmers = new HashSet<>();
    private Set<UserDto> users = new HashSet<>();
    private Set<TeamDto> teams = new HashSet<>();

}
