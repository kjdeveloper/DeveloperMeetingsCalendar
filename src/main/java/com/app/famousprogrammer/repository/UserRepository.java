package com.app.famousprogrammer.repository;

import com.app.famousprogrammer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
