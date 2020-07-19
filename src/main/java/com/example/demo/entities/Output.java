package com.example.demo.entities;

import javax.persistence.*;


@Entity
@Table(name="output")
public class Output {
    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }

    @Id
    private int output;

}
