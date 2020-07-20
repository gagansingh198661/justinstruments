package com.example.demo.services;

import com.example.demo.entities.EmailRecipient;
import com.example.demo.repositories.EmailRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailRecipientService {

    @Autowired
    private EmailRepositoryInterface repositoryInterface;
    public List<EmailRecipient> getAll(){
        return repositoryInterface.findAll();
    }
}
