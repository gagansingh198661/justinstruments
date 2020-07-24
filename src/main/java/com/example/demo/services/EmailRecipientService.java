package com.example.demo.services;

import com.example.demo.entities.EmailRecipient;
import com.example.demo.repositories.EmailRepositoryInterface;
import com.example.demo.utilities.ExcelUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class EmailRecipientService {

    @Autowired
    private EmailRepositoryInterface repositoryInterface;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailRecipientService.class);

    public List<EmailRecipient> getAll(){
        try {
            return repositoryInterface.findAll();
        }catch(Exception e){
            LOGGER.error("Error Retrieving Email List");
        }
        return new LinkedList<EmailRecipient>();
    }
}
