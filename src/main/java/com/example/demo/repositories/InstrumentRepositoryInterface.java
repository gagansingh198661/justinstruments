package com.example.demo.repositories;


import com.example.demo.entities.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstrumentRepositoryInterface extends JpaRepository<Instrument,Long> {

    public List<Instrument> findByTagNo(String tagNo);

     List<Instrument> findByInstrumentSerialNo(String instrumentSerialNo);

     Instrument findByCalRefNo(String cal);

     List<Instrument> findByClientId(String clientId);

     List<Instrument> findByTagNoAndClientId(String tagno,String clientid);
}
