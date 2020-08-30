package com.example.demo.services;

import com.example.demo.dtos.FormDto;
import com.example.demo.entities.Client;
import com.example.demo.entities.Instrument;
import com.example.demo.utilities.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FormFactory {

    @Autowired
    private ClientService cService;

    @Autowired
    private InstrumentService instrumentService;


    public FormDto getFormObject(String type,Object o){
        if(type.equalsIgnoreCase(Constants.CLIENT_NAME)){
            return makeFormObjectFromClientName((String)o);
        }else if(type.equalsIgnoreCase(Constants.CAL_REF_NO)){
            return makeFormObjectFromClientRefNo((String)o);
        }
        return new FormDto();
    }

    private FormDto makeFormObjectFromClientRefNo(String cal_ref_no) {
        Instrument instrument=instrumentService.getInstrumentByCalRefNo(cal_ref_no);
        FormDto dto=new FormDto();
        fillFormDto(instrument,dto);
        dto.setClientCalibrationRefNo(cal_ref_no);

        String clientId=instrument.getClientId();
        Client client=cService.getClient(clientId);
        fillFormDto(client,dto);
        return dto;
    }

    private FormDto makeFormObjectFromClientName(String clientName) {
        Client client=cService.getClient(clientName);
        FormDto dto=new FormDto();
        fillFormDto(client,dto);
        return dto;
    }

    private void fillFormDto(Client client,FormDto dto) {
        if (client != null){
            String addressString = client.getAddress();
            String[] addresses = addressString.split("##");
            dto.setClientAddresses(addresses);
            dto.setClientName(client.getName());
            dto.setFax(client.getFax());
            dto.setPhone(client.getPhone());
            dto.setEmail(client.getEmail());
        }
    }

    private void fillFormDto(Instrument instrument,FormDto dto){
        if(instrument!=null) {
            dto.setInstrumentSerialNo(instrument.getInstrumentSerialNo());
            dto.setMake(instrument.getMake());
            dto.setModel(instrument.getModel());
            dto.setDueDate(instrument.getDate());
            dto.setTagNo(instrument.getTagNo()!=null?instrument.getTagNo():"");

        }
    }
}
