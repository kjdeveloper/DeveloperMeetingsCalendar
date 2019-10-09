package com.app.famousprogrammer.repository;

import com.app.famousprogrammer.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

interface TeamRepository extends JpaRepository<Team, Long> {
}
