package com.example.demo.dtos;

import java.io.File;
import java.sql.Blob;
import java.util.Date;

public class ReportDTO {
    public File getReportFile() {
        return reportFile;
    }

    public void setReportFile(File reportFile) {
        this.reportFile = reportFile;
    }

    private File reportFile;
    private int reportId;
    private String reportName;
    private Date reportCreationDate;
    private int clientId;

    public Blob getReportBlob() {
        return reportBlob;
    }

    public void setReportBlob(Blob reportBlob) {
        this.reportBlob = reportBlob;
    }

    private Blob reportBlob;




    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    private String clientName;
    private Date reportExpiryDate;


    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Date getReportCreationDate() {
        return reportCreationDate;
    }

    public void setReportCreationDate(Date reportCreationDate) {
        this.reportCreationDate = reportCreationDate;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public Date getReportExpiryDate() {
        return reportExpiryDate;
    }

    public void setReportExpiryDate(Date reportExpiryDate) {
        this.reportExpiryDate = reportExpiryDate;
    }

}
