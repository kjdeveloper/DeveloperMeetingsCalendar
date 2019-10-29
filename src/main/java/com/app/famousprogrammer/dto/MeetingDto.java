package com.app.famousprogrammer.dto;

import com.app.famousprogrammer.model.enums.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeetingDto {

    private Long id;
    private String topic;
    private String description;
    private LocalDateTime startDate;
    private double duration;
    private City placeOfMeeting;

}
