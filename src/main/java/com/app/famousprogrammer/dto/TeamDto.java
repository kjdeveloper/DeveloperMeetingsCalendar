package com.app.famousprogrammer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamDto {


    private Long id;
    private String name;
    private LocalDate creationDate;
    private Long teamLeaderId;
    private Set<ProgrammerDto> programmers;
    private Set<MeetingDto> meetings;
}
