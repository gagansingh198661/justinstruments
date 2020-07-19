package com.example.demo.dtos;

public class OutputResult {
    double standardValue;
    double calculatedValue;
    double tolerance;
    boolean result;

    public double getStandardValue() {
        return standardValue;
    }

    public void setStandardValue(double standardValue) {
        this.standardValue = standardValue;
    }

    public double getCalculatedValue() {
        return calculatedValue;
    }

    public void setCalculatedValue(double calculatedValue) {
        this.calculatedValue = calculatedValue;
    }

    public double getTolerance() {
        return tolerance;
    }

    public void setTolerance(double tolerance) {
        this.tolerance = tolerance;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }


}
