package com.example.demo.repositories;

import com.example.demo.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepositoryInterface extends JpaRepository<Report,Long> {

    //@Query(value="SELECT p FROM Report p WHERE p.expiryDate between CURRENT_DATE() and ADDDATE(CURRENT_DATE(), INTERVAL 1 MONTH))")
    @Query(nativeQuery = true,value="SELECT * FROM Report p WHERE p.expiry_date between CURRENT_DATE() and ADDDATE(CURRENT_DATE(), INTERVAL 1 MONTH) and p.report_sent=0")
    public List<Object[]> getReportsAboutToExpire();

}
