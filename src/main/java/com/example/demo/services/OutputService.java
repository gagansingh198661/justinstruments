package com.example.demo.services;

import com.example.demo.entities.Output;
import com.example.demo.repositories.OutputRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class OutputService {

    @Autowired
    private OutputRepository2 outputRepository;

    public List<Output> getAll(){
         List<Output> outputList=new LinkedList<>();
        try{
            outputList= outputRepository.findAll();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            return outputList;
        }
    }
}
