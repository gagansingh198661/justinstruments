package com.example.demo.entities;

import javax.persistence.*;
import java.util.Map;


@Entity
@Table(name="excel_parameters")
public class ExcelResult {

    @Transient
    private Map<Integer, String> indexNameMap;

    public String getExcelName() {
        return excelName;
    }

    public void setExcelName(String excelName) {
        this.excelName = excelName;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    @Id
    private String excelName;

    private String parameters;

    public Map<Integer, String> getHashmap() {
        return indexNameMap;
    }

    public void setHashmap(Map hashmap) {
        this.indexNameMap = hashmap;
    }
}
