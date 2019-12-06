package com.app.famousprogrammer.service;

import com.app.famousprogrammer.dto.ProgrammerDto;
import com.app.famousprogrammer.exceptions.MyException;
import com.app.famousprogrammer.model.Programmer;
import com.app.famousprogrammer.repository.ProgrammerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class ProgrammerService {


private final ProgrammerRepository programmerRepository;
private final PasswordEncoder passwordEncoder;

    public String registerProgrammer(ProgrammerDto programmerDto){

        if (programmerDto == null){
            throw new MyException("programmer registration - user dto is null");
        }

        if (!Objects.equals(programmerDto.getUserDto().getPassword(), programmerDto.getUserDto().getPasswordConfirmation())){
            throw new MyException("programmer registration - passwords don't match");
        }

        if (programmerRepository.findByUser_Name(programmerDto.getUserDto().getUsername()).isPresent()){
            throw new MyException("programmer registration - username currently exists in our database");
        }

        if (programmerRepository.findByUser_Email(programmerDto.getUserDto().getEmail()).isPresent()){
            throw new MyException("programmer registration - email currently exists in our database");
        }

        Programmer programmer = Mappers.fromProgrammerDtoToProgrammer(programmerDto);
        programmer.getUser().setPassword(passwordEncoder.encode(programmerDto.getUserDto().getPassword()));

        Programmer inserted = programmerRepository.save(programmer);
        return  inserted.getUser().getUsername() + " has been registered.";
    }
}

