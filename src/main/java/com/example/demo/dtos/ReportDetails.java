package com.example.demo.dtos;

import java.io.FileOutputStream;

public class ReportDetails {
    private String reportName;
    private FileOutputStream fileOutputStream;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private String path;
    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public FileOutputStream getFileOutputStream() {
        return fileOutputStream;
    }

    public void setFileOutputStream(FileOutputStream fileOutputStream) {
        this.fileOutputStream = fileOutputStream;
    }


}
