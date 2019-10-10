package com.app.famousprogrammer.validators;

import com.app.famousprogrammer.dto.TeamDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class TeamValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(TeamDto.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        TeamDto teamDto = (TeamDto)o;

        if (!teamDto.getName().matches("[\\w+]{5,15}")){
            errors.rejectValue("team name", "team name must be at least 5 and not more than 15 characters");
        }
    }
}
