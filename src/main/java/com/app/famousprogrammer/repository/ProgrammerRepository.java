package com.app.famousprogrammer.repository;

import com.app.famousprogrammer.model.Programmer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProgrammerRepository extends JpaRepository<Programmer, Long> {
    
    Optional<Programmer> findByUsername(String username);
    Optional<Programmer> findByEmail(String email);
}
