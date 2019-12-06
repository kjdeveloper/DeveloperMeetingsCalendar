package com.app.famousprogrammer.service;

import com.app.famousprogrammer.dto.MeetingDto;
import com.app.famousprogrammer.dto.ProgrammerDto;
import com.app.famousprogrammer.dto.UserDto;
import com.app.famousprogrammer.model.Meeting;
import com.app.famousprogrammer.model.Programmer;
import com.app.famousprogrammer.model.User;
import com.app.famousprogrammer.model.enums.Roles;
import com.app.famousprogrammer.repository.MeetingRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public interface Mappers {

    static UserDto fromUserToUserDto(User user){
        return user == null ? null : UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .birthDate(user.getBirthDate())
                .email(user.getEmail())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }

    static User fromUserDtoToUser(UserDto userDto){
        return userDto == null ? null : User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .birthDate(userDto.getBirthDate())
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .role(userDto.getRole())
                .enabled(false)
                .build();
    }

    static ProgrammerDto fromProgrammerToProgrammerDto(Programmer programmer){
        return programmer == null ? null : ProgrammerDto.builder()
                .id(programmer.getId())
                .userDto(Mappers.fromUserToUserDto(programmer.getUser()))
                .skills(programmer.getSkills())
                .meetings(getAllMeetingsForProgrammerDto(programmer.getMeetings()))
                .build();
    }

    static Programmer fromProgrammerDtoToProgrammer(ProgrammerDto programmerDto){
        return programmerDto == null ? null : Programmer.builder()
                .id(programmerDto.getId())
                .user(Mappers.fromUserDtoToUser(programmerDto.getUserDto()))
                .skills(programmerDto.getSkills())
                .meetings(new HashSet<>())
                .build();
    }

    private static Set<String> getAllMeetingsForProgrammerDto(Set<Meeting> meetings){
        return meetings
                .stream()
                .map(Meeting::getTopic)
                .collect(Collectors.toSet());
    }

}
