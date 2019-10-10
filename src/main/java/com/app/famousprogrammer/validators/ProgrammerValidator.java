package com.app.famousprogrammer.validators;

import com.app.famousprogrammer.dto.ProgrammerDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

public class ProgrammerValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(ProgrammerDto.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ProgrammerDto programmerDto = (ProgrammerDto)o;

        if (!programmerDto.getName().matches("[A-Z]?[a-z]+")){
            errors.rejectValue("name", "use only the letters of the alphabet");
        }

        if (!programmerDto.getSurname().matches("[A-Z]?[a-z]+")){
            errors.rejectValue("surname", "use only the letters of the alphabet");
        }

        if (!programmerDto.getUsername().matches("[\\w+]{5,15}")){
            errors.rejectValue("username", "username must be at least 5 and not more than 15 characters");
        }

        if (programmerDto.getBirthDate().compareTo(LocalDate.now().minusYears(18)) > 0){
            errors.rejectValue("birth day", "you must be at least 18 years old");
        }
    }
}
