package com.example.demo.services;

import com.example.demo.entities.ExcelResult;
import com.example.demo.repositories.ExcelRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExcelService {

    @Autowired
    private ExcelRepositoryInterface excelRepositoryInterface;

    public List<ExcelResult> findAll(){
        List<ExcelResult>excelResultList= excelRepositoryInterface.findAll();
        List<ExcelResult> excelResults=excelResultList.stream().map(o->{
            String[] parameters=o.getParameters().split(",");
            Map<Integer, String> parametersMap=new HashMap<>();
            int index=0;
            for(String param:parameters){
                parametersMap.put(index,param);
                index++;
            }
            o.setHashmap(parametersMap);
            return o;
        }).collect(Collectors.toList());
        return excelResults;
    }


}
