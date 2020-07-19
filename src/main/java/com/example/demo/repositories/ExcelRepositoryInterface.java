package com.example.demo.repositories;

import com.example.demo.entities.ExcelResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcelRepositoryInterface extends JpaRepository<ExcelResult,Long> {
}
