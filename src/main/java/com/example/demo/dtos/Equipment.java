package com.example.demo.dtos;

import java.util.Date;

public class Equipment {


    public int getAsset_no() {
        return asset_no;
    }

    public void setAsset_no(int asset_no) {
        this.asset_no = asset_no;
    }

    public Date getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(Date expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel_no() {
        return model_no;
    }

    public void setModel_no(String model_no) {
        this.model_no = model_no;
    }

    public String getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    Date expiry_date;
    String manufacturer;
    String model_no;
    String serial_no;
    String description;
    int asset_no;
}
