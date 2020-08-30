package com.example.demo.services;

import com.example.demo.entities.Client;
import com.example.demo.repositories.ClientRepositoryInterface;
import com.example.demo.utilities.Utility;
import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
public class ClientService {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ClientService.class);

    @Autowired
    ClientRepositoryInterface clientRepository;

    public void saveAll(List<Client> clientList){
        try{
            if(clientList==null||clientList.isEmpty())
                return;
            clientRepository.deleteAll();
            for(Client client:clientList) {
                try {
                  client=  clientRepository.save(client);
                }catch(Exception e){
                    LOGGER.error("Error Occured while saving", e);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            if(e instanceof EntityExistsException) {
                Utility.showPopup(Alert.AlertType.ERROR,"Client Of Same Name in Client Sheet, Please Check Data");
            }
        }
    }

    public List<Client> getAll(){
        return clientRepository.findAll();
    }

    public Client getClient(String name){
        return clientRepository.findByName(name);
    }

}
