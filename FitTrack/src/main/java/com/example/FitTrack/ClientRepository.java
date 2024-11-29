package com.example.FitTrack;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    // Custom query to find client by username and password
    Client findByUsernameAndPassword(String username, String password);
}
