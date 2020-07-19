package com.example.demo.repositories;



import com.example.demo.dtos.ExcelResultDto;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
@Component
public class ExcelRepository implements  Repository{

    @Override
    public List<ExcelResultDto> getAll() {
        List<ExcelResultDto> excelList=new LinkedList<>();
        try{
            Connection conn = ConnectionUtility.getConnection();
            String sql = "select * from excel_parameters";
            PreparedStatement statement=conn.prepareStatement(sql);
            ResultSet set=statement.executeQuery();
            excelList=getExcelResultSet(set);

        }catch(Exception e){
            System.out.println("exception Occured : "+e.getStackTrace());
        }
        return excelList;
    }

    @Override
    public List getDTO(int id) {
        return null;
    }

    @Override
    public List getDTO(String id) {
        return null;
    }

    private List<ExcelResultDto> getExcelResultSet(ResultSet set) throws SQLException {
        List<ExcelResultDto> equipmentList=new LinkedList<>();
        while(set.next()){
            ExcelResultDto equipment = new ExcelResultDto();
            equipment.setSheetName(set.getString("excel_name"));
            String[] parameters= set.getString("parameters").split(",");
            Map<Integer, String> parametersMap=new HashMap<>();
            int index=0;
            for(String param:parameters){
                parametersMap.put(index,param);
                index++;
            }
            equipment.setHashmap(parametersMap);

            equipmentList.add(equipment);
        }
        return equipmentList;
    }

}
