package com.example.demo.utilities;

import com.example.demo.entities.ApplicationProperty;
import com.example.demo.entities.EmailRecipient;
import com.example.demo.entities.Report;
import com.example.demo.services.ApplicationPropertyService;
import com.example.demo.services.EmailRecipientService;
import com.example.demo.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Map;

@Component
public class ThreadUtility {

    @Autowired
    private ReportService reportService;

    @Autowired
    private EmailUtility emailUtility;

    @Autowired
    private EmailRecipientService emailRecipientService;

    @Autowired
    private ApplicationPropertyService applicationPropertyService;

    public   void checkForExpiryOfReports(){
       new Thread(()->{
            List<EmailRecipient> emailRecipientList=emailRecipientService.getAll();
            String[] emails=new String[emailRecipientList.size()];
            List<Report> reports=reportService.getReportsAboutToExpire();
           if(reports.size()==0){
               return;
           }
            int index=0;
            for(EmailRecipient emailRec:emailRecipientList){
                emails[index]=emailRec.getEmail();
                index++;
            }
            Map<String,String> propertyMap=applicationPropertyService.getApplicationProperties();
            String body="Instrument's Due Date will be Coming in 1 Month";
            for(Report reportDTO:reports){
                 String result=emailUtility.sendMail(propertyMap.get("mail.smtp.user"),emails,"Equipment Is Going To Expire",
                        body,reportDTO);
                 if(result.equalsIgnoreCase("success")){
                     reportDTO.setReportSent(true);
                     reportService.save(reportDTO);
                 }
            }

        }).start();
    }
}
