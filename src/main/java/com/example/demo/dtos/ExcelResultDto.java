package com.example.demo.dtos;

import java.util.Map;

public class ExcelResultDto {
    private String sheetName;
    private Map<Integer, String> indexNameMap;
    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public Map<Integer, String> getHashmap() {
        return indexNameMap;
    }

    public void setHashmap(Map hashmap) {
        this.indexNameMap = hashmap;
    }

}
