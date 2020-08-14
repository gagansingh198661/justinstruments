package com.example.demo.entities;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="instrument")
public class Instrument {

    private String serialNo;

    @Id
    @Column(name="tag_no")
    private String tagNo;

    @Column(name="instrument_serial_no")
    private String instrumentSerialNo;

    private String make;

    private String model;

    private String cal_ref_no;

    private Date date;

    private String remarks;

    @NotNull
    private String location;

    public String getRanges() {
        return ranges;
    }

    public void setRanges(String ranges) {
        this.ranges = ranges;
    }

    @Column(name="ranges")
    private String ranges;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;



    public String getTagNo() {
        return tagNo;
    }

    public void setTagNo(String tagNo) {
        this.tagNo = tagNo;
    }

    public String getInstrumentSerialNo() {
        return instrumentSerialNo;
    }

    public void setInstrumentSerialNo(String instrumentSerialNo) {
        this.instrumentSerialNo = instrumentSerialNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instrument that = (Instrument) o;
        boolean equal=true;
        if(!instrumentSerialNo.equals(that.instrumentSerialNo)){
            equal=false;
        }
        if(!tagNo.equals(that.tagNo)){
            equal=false;
        }
        return equal;

    }

    @Override
    public int hashCode() {
        return Objects.hash(instrumentSerialNo);
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
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

    public String getCal_ref_no() {
        return cal_ref_no;
    }

    public void setCal_ref_no(String cal_ref_no) {
        this.cal_ref_no = cal_ref_no;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }








}
