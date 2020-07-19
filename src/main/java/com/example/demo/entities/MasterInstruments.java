package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="master_instruments")
public class MasterInstruments {

    @Id
    private long assetNo;

    private String description;

    private String make;

    private String model;

    private String instrumentSerialNo;

    private Date calDate;

    public long getAsset_no() {
        return assetNo;
    }

    public void setAsset_no(long asset_no) {
        this.assetNo = asset_no;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getInstrumentSerialNo() {
        return instrumentSerialNo;
    }

    public void setInstrumentSerialNo(String instrumentSerialNo) {
        this.instrumentSerialNo = instrumentSerialNo;
    }

    public Date getCalDate() {
        return calDate;
    }

    public void setCalDate(Date calDate) {
        this.calDate = calDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    private Date dueDate;

}
