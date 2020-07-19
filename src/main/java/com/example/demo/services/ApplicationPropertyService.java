package com.example.demo.services;

import com.example.demo.entities.ApplicationProperty;
import com.example.demo.repositories.ApplicationRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ApplicationPropertyService {

    @Autowired
    private ApplicationRepository2 applicationRepository2;

    public Map<String,String> getApplicationProperties(){
        List<ApplicationProperty> applicationPropertyList=new LinkedList<>();
        try {
            applicationPropertyList = applicationRepository2.findAll();
            Map<String,String> propertiesMap=new HashMap<>();
            propertiesMap=applicationPropertyList.stream().collect(Collectors.toMap(ApplicationProperty::getPropertyKey,ApplicationProperty::getPropertyValue));
            return propertiesMap;
        }catch(Exception e){

        }finally {

        }
        return null;

    }
}
