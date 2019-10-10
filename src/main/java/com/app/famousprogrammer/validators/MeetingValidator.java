package com.app.famousprogrammer.validators;

import com.app.famousprogrammer.dto.MeetingDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

public class MeetingValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(MeetingDto.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        MeetingDto meetingDto = (MeetingDto)o;

        if (!meetingDto.getTopic().matches("([A-Z ]+[0-9]*)+")){
            errors.rejectValue("topic", "use only capital letters to write topic of meeting");
        }

        if (!meetingDto.getDescription().matches("[\\w+ ]{5,50}")){
            errors.rejectValue("description", "description must be at least 5 and not more than 50 characters");
        }

        if (!(meetingDto.getStartDate().compareTo(LocalDateTime.now()) > 0)){
            errors.rejectValue("start date", "start date must be from the future");
        }

        if (!(meetingDto.getDuration() <= 0.5)){
            errors.rejectValue("duration", "meeting duration must be at least 0.5 hours" );
        }
    }
}
