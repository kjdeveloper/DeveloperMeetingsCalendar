package com.app.famousprogrammer.repository;

import com.app.famousprogrammer.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByUsername(String username);
    Optional<Client> findByEmail(String email);


}
