package com.example.demo.repositories;



import com.example.demo.dtos.Equipment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class EquipmentRepository implements Repository<Equipment>{

    private List<Equipment> getEquipment(ResultSet set) throws SQLException {
        List<Equipment> equipmentList=new LinkedList<>();
        while(set.next()){
            Equipment equipment = new Equipment();
            equipment.setAsset_no(Integer.valueOf(set.getString("asset_no")));
            equipment.setManufacturer(set.getString("manufacturer"));
            equipment.setDescription(set.getString("description"));
            equipment.setModel_no(set.getString("model_no"));
            equipment.setExpiry_date(set.getDate("expiry_date"));
            equipment.setSerial_no(set.getString("serial_no"));
            equipmentList.add(equipment);
        }
        return equipmentList;
    }

    @Override
    public List<Equipment> getAll() {
        List<Equipment> equipmentList=new LinkedList<>();
        try{
            Connection conn = ConnectionUtility.getConnection();
            String sql = "select * from test_equipment";
            PreparedStatement statement=conn.prepareStatement(sql);
            ResultSet set=statement.executeQuery();
            equipmentList=getEquipment(set);

        }catch(Exception e){
            System.out.println("exception Occured : "+e.getStackTrace());
        }
        return equipmentList;
    }

    @Override
    public List<Equipment> getDTO(int id) {
        List<Equipment> equipmentList=new LinkedList<>();
        try{
            Connection conn = ConnectionUtility.getConnection();
            String sql = "select * from test_equipment where asset_no="+id;
            PreparedStatement statement=conn.prepareStatement(sql);
            ResultSet set=statement.executeQuery();
            equipmentList=getEquipment(set);

        }catch(Exception e){
            System.out.println("exception Occured : "+e.getStackTrace());
        }
        return equipmentList;
    }

    @Override
    public List<Equipment> getDTO(String id) {
        return null;
    }
}
