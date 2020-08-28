package com.example.demo.dtos;


import java.util.Date;

public class FormDto {
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    private String clientName;

    public String[] getClientAddresses() {
        return clientAddresses;
    }

    public void setClientAddresses(String[] clientAddresses) {
        this.clientAddresses = clientAddresses;
    }

    public String getClientCalibrationRefNo() {
        return clientCalibrationRefNo;
    }

    public void setClientCalibrationRefNo(String clientCalibrationRefNo) {
        this.clientCalibrationRefNo = clientCalibrationRefNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getInstrumentSerialNo() {
        return instrumentSerialNo;
    }

    public void setInstrumentSerialNo(String instrumentSerialNo) {
        this.instrumentSerialNo = instrumentSerialNo;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    private String[] clientAddresses;
    private String clientCalibrationRefNo;
    private String phone;
    private String email;
    private String fax;
    private String instrumentSerialNo;
    private String make;
    private String model;
    private Date dueDate;

    public String getTagNo() {
        return tagNo;
    }

    public void setTagNo(String tagNo) {
        this.tagNo = tagNo;
    }

    private String tagNo;

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }


}
