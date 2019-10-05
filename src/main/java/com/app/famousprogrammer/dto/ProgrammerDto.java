package com.app.famousprogrammer.dto;

import com.app.famousprogrammer.model.enums.Skills;
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
public class ProgrammerDto {

    private Long id;
    private String name;
    private String surname;
    private String username;
    private LocalDate birthDate;
    private String email;
    private Set<MeetingDto> meetings;
    private Set<TeamDto> teams;
    private Set<Skills> skills;

}
