package com.example.demo.services;

import com.example.demo.entities.MasterInstruments;
import com.example.demo.repositories.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterInstrumentsService {

    @Autowired
    private MasterRepository masterRepository;

    public void saveAll(List<MasterInstruments> masterInstrumentsList){
        try {
            masterRepository.saveAll(masterInstrumentsList);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public MasterInstruments getMasterInstrumentbyAssetNo(String value){
        return masterRepository.findByAssetNo(Long.valueOf(value));
    }
}
