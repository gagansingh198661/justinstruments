package com.example.demo.repositories;


import com.example.demo.entities.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstrumentRepositoryInterface extends JpaRepository<Instrument,Long> {

    public List<Instrument> findByTagNo(String tagNo);

    public List<Instrument> findByInstrumentSerialNo(String instrumentSerialNo);

}
