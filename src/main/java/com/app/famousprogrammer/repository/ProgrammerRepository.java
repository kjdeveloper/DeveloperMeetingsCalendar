package com.app.famousprogrammer.repository;

import com.app.famousprogrammer.model.Programmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgrammerRepository extends JpaRepository<Programmer, Long> {
}
