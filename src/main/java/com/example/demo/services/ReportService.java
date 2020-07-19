package com.example.demo.services;

import com.example.demo.entities.Report;
import com.example.demo.repositories.ReportRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Blob;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    private ReportRepositoryInterface reportRepository;

    public Report save(Report report){
       try {
           return reportRepository.save(report);
       }catch(Exception e){
           e.printStackTrace();
       }
       return null;
    }

    public List<Report> getReportsAboutToExpire(){
        List<Report> reportLists=null;
        try{
        List<Object[]> results=reportRepository.getReportsAboutToExpire();
        reportLists=new LinkedList<>();
        for(Object[] result: results){
            Report report=new Report();
            if(result[0]!=null) {
                report.setReportId((Integer) result[0]);
            }
            if(result[1]!=null){
               report.setClientName((String)result[1]);
               System.out.println(result[1].getClass());
            }
            if(result[2]!=null)
            report.setExpiryDate((Date)result[2]);
            if(result[3]!=null)
            report.setReportName((String) result[3]);
            if(result[4]!=null)
                report.setClientId((Integer) result[4]);
            if(result[6]!=null)
            report.setReportCreationDate((Date)result[6]);
            if(result[7]!=null) {
                report.setReportFile((byte[]) result[7]);
            }
            reportLists.add(report);
        }
        }catch(Exception e){
            e.printStackTrace();;
        }
        return reportLists;
    }
}
