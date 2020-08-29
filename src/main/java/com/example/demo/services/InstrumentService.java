package com.example.demo.services;


import com.example.demo.entities.Instrument;
import com.example.demo.repositories.InstrumentRepositoryInterface;


import com.example.demo.utilities.Utility;
import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class InstrumentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InstrumentService.class);

    @Autowired
    private InstrumentRepositoryInterface instrumentRepository;

    public void saveAll(List<Instrument> instrumentSet){

        for(Instrument instruemnt:instrumentSet) {
            try {
                instrumentRepository.save(instruemnt);
            }catch(Exception e){

                LOGGER.error("Error Has Occured :",e);
            }
        }
    }

    public List<Instrument> getInstruments(String tag_no){
       try {
           return instrumentRepository.findByTagNo(tag_no);
       }catch(Exception e){
           e.printStackTrace();
       }
       return new LinkedList<>();
    }

    public List<Instrument> getInstrumentsBySerialNo(String serialNo){
        try {
            return instrumentRepository.findByInstrumentSerialNo(serialNo);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new LinkedList<>();
    }

    public Instrument getInstrumentByCalRefNo(String cal_ref){
        try{
            return instrumentRepository.findByCalRefNo(cal_ref);
        }catch (Exception e){
            LOGGER.error("Error for cal_ref :"+cal_ref,e);
        }
        return null;
    }

    public  List<Instrument> findAll(){
        return instrumentRepository.findAll();
    }

    public List<Instrument> getInstrumentsByClientId(String clientId){
        return instrumentRepository.findByClientId(clientId);
    }
}
