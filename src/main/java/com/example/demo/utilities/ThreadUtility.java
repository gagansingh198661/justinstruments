package com.example.demo.utilities;

import com.example.demo.dtos.ReportDTO;
import com.example.demo.entities.EmailRecipient;
import com.example.demo.entities.Report;
import com.example.demo.repositories.ConnectionUtility;
import com.example.demo.repositories.ReportRepository;
import com.example.demo.services.EmailRecipientService;
import com.example.demo.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Properties;
@Component
public class ThreadUtility {

    @Autowired
    private ReportService reportService;

    @Autowired
    private EmailUtility emailUtility;

    @Autowired
    private EmailRecipientService emailRecipientService;

    public   void checkForExpiryOfReports(){
       new Thread(()->{
            List<EmailRecipient> emailRecipientList=emailRecipientService.getAll();
            String[] emails=new String[emailRecipientList.size()];
            List<Report> reports=reportService.getReportsAboutToExpire();
            String body="Your Equipment Will Expire in 1 Month";
            for(Report reportDTO:reports){
                 String result=emailUtility.sendMail("budsy.remo@gmail.com",emails,"Equipment Is Going To Expire",
                        body,reportDTO);
                 if(result.equalsIgnoreCase("success")){
                     reportDTO.setReportSent(true);
                     reportService.save(reportDTO);
                 }
            }

        }).start();
    }
}
