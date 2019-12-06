package com.app.famousprogrammer.service;

import com.app.famousprogrammer.dto.UserDto;
import com.app.famousprogrammer.exceptions.MyException;
import com.app.famousprogrammer.model.User;
import com.app.famousprogrammer.model.enums.Roles;
import com.app.famousprogrammer.repository.MeetingRepository;
import com.app.famousprogrammer.repository.ProgrammerRepository;
import com.app.famousprogrammer.repository.TeamRepository;
import com.app.famousprogrammer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class SecurityService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String registerUser(UserDto userDto){

        if (userDto == null){
            throw new MyException("user registration - user dto is null");
        }

        if (!Objects.equals(userDto.getPassword(), userDto.getPasswordConfirmation())){
            throw new MyException("user registration - passwords don't match");
        }

        if (userRepository.findByUsername(userDto.getUsername()).isPresent()){
            throw new MyException("user registration - username currently exists in our database");
        }

        if (userRepository.findByEmail(userDto.getEmail()).isPresent()){
            throw new MyException("user registration - email currently exists in our database");
        }

        User user = Mappers.fromUserDtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User inserted = userRepository.save(user);
        return  inserted.getUsername() + " has been registered.";
    }
}

