package com.app.famousprogrammer.repository;

import com.app.famousprogrammer.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
