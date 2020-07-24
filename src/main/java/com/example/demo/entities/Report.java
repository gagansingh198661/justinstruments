package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import java.util.Date;
@Entity
@Table(name="report")
public class Report {
    public byte[] getReportFile() {
        return reportFile;
    }

    public void setReportFile(byte[] reportFile) {
        this.reportFile = reportFile;
    }

    private boolean reportSent;

    @Lob
    private byte[] reportFile;

    @Id
    private int reportId;
    private String reportName;
    private Date reportCreationDate;
    private int clientId;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    private String clientName;

    public boolean isReportSent() {
        return reportSent;
    }

    public void setReportSent(boolean reportSent) {
        this.reportSent = reportSent;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    private Date expiryDate;


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


}
