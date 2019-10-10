package com.app.famousprogrammer.validators;

import com.app.famousprogrammer.dto.UserDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UserDto.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDto userDto = (UserDto)o;

        if (!userDto.getName().matches("[A-Z]?[a-z]+")){
            errors.rejectValue("name", "use only the letters of the alphabet");
        }

        if (!userDto.getSurname().matches("[A-Z]?[a-z]+")){
            errors.rejectValue("surname", "use only the letters of the alphabet");
        }

        if (userDto.getBirthDate().compareTo(LocalDate.now().minusYears(18)) > 0){
            errors.rejectValue("birth day", "you must be at least 18 years old");
        }

    }
}
