package com.example.demo.utilities;



import com.example.demo.entities.Report;

import com.example.demo.services.ApplicationPropertyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.util.Map;
import java.util.Properties;
@Component
public class EmailUtility {

    @Autowired
    private ApplicationPropertyService applicationPropertyService;

    public Map<String,String> mailProperties() {
        Map<String,String> properties=applicationPropertyService.getApplicationProperties();

        return properties;
    }

    public String sendMail(String from, String[] to, String subject, String msgBody, Report reportdto) {
        Map<String,String> propertiesMap= mailProperties();
        Properties properties=new Properties();
        for(Map.Entry<String,String> entry:propertiesMap.entrySet()){
            properties.put(entry.getKey(),entry.getValue());
        }
        Session mailSession = Session.getInstance(properties, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        propertiesMap.get("mail.smtp.user"), propertiesMap.get("mail.smtp.password"));// Specify the Username and the PassWord
            }
        });

        mailSession.setDebug(false);

        try {
            Address[] addresses=new Address[to.length];
            int index=0;
            for(String email:to){
                Address address1 = new InternetAddress(email);
                addresses[index]=address1;
                index++;
            }
            Transport transport = mailSession.getTransport();

            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject(subject);
            message.setFrom(new InternetAddress(from));

            message.addRecipients(Message.RecipientType.TO,addresses);

            MimeMultipart multipart = new MimeMultipart();

            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            byte [] bytes = reportdto.getReportFile();
            File tempFile= File.createTempFile(".pdf",reportdto.getReportName());

            OutputStream out = new FileOutputStream(tempFile);
            //byte[] buff = reportdto.getByteArr();
            out.write(bytes);
            out.close();

            DataSource source = new FileDataSource(tempFile); // ex : "C:\\test.pdf"
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(reportdto.getReportName()); // ex :
            //attachmentBodyPart.setContent(msgBody, "text/html");

            multipart.addBodyPart(attachmentBodyPart);
            message.setContent(multipart);

            transport.connect();
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            transport.close();
            return "SUCCESS";
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return "INVALID_EMAIL";
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }
        return "ERROR";
    }

    public static void main(String args[]) {
        /*System.out.println(new EmailUtility().sendMail("budsy.remo@gmail.com", "gagan_singh198661@yahoo.com"
                , "Subject", "Message",null));*/
    }
}
