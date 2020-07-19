package com.example.demo.services;

import com.example.demo.entities.Client;
import com.example.demo.repositories.ClientRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepositoryInterface clientRepository;

    public void saveAll(List<Client> clientList){
        try{
            clientRepository.saveAll(clientList);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Client> getAll(){
        return clientRepository.findAll();
    }

    public Client getClient(String name){
        return clientRepository.findByName(name);
    }
}
