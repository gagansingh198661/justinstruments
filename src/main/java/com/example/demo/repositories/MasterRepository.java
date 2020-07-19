package com.example.demo.repositories;

import com.example.demo.entities.MasterInstruments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRepository extends JpaRepository<MasterInstruments,Long> {

    public MasterInstruments findByAssetNo(long id);
}
