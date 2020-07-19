package com.example.demo.repositories;

import com.example.demo.entities.ApplicationProperty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository2 extends JpaRepository<ApplicationProperty,Long> {

}
