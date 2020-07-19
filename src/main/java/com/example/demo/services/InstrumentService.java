package com.example.demo.services;


import com.example.demo.entities.Instrument;
import com.example.demo.repositories.InstrumentRepositoryInterface;
import com.example.demo.utilities.ExcelUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class InstrumentService {

    private static final Logger LOGGER = LogManager.getLogger(InstrumentService.class);

    @Autowired
    private InstrumentRepositoryInterface instrumentRepository;

    public void saveAll(Set<Instrument> instrumentSet){

        for(Instrument instruemnt:instrumentSet) {
            try {
                instrumentRepository.save(instruemnt);
            }catch(Exception e){

                LOGGER.error("Error Has Occured :",e);
                System.out.println("error occured for instruemnt :"+instruemnt.getInstrumentSerialNo()+"  : "+instruemnt.getTagNo()
                +"  : "+instruemnt.getCal_ref_no()+"  : "+instruemnt.getLocation()+"  "+instruemnt.getRanges());
                e.printStackTrace();
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

    public  List<Instrument> findAll(){
        return instrumentRepository.findAll();
    }
}
