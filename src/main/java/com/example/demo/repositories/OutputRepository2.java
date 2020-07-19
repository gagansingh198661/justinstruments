package com.example.demo.repositories;


import com.example.demo.entities.Output;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputRepository2 extends JpaRepository<Output,Long> {
}
