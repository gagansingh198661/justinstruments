package com.example.demo.repositories;

import com.example.demo.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepositoryInterface extends JpaRepository<Client,Long> {
    public Client findByName(String name);
}
