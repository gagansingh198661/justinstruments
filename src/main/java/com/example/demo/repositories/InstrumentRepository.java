package com.example.demo.repositories;


import com.example.demo.entities.Instrument;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class InstrumentRepository implements Repository<Instrument> {
    @Override
    public List<Instrument> getAll() {
        List<Instrument> instrumentList=new LinkedList<>();
        try {
            Connection conn = ConnectionUtility.getConnection();
            String sql = "select * from instrument";
            PreparedStatement statement=conn.prepareStatement(sql);
            ResultSet set=statement.executeQuery();
            instrumentList=getInstrumentList(set);
        }catch(Exception e){

        }
        return instrumentList;
    }

    @Override
    public List<Instrument> getDTO(int id) {
        return null;
    }

    @Override
    public List<Instrument> getDTO(String id) {
        List<Instrument> instrumentList=new LinkedList<>();
        try {
            Connection conn = ConnectionUtility.getConnection();
            String sql = "select * from Instrument where tag_no="+"'"+id+"'";
            PreparedStatement statement=conn.prepareStatement(sql);
            ResultSet set=statement.executeQuery();
            instrumentList=getInstrumentList(set);
        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }
        return instrumentList;
    }

    private List<Instrument> getInstrumentList(ResultSet set) throws SQLException {
        List<Instrument> instrumentList=new LinkedList<>();
        while(set.next()){
            Instrument instrument = new Instrument();

            instrument.setCal_ref_no(set.getString("cal_ref_no"));
            instrument.setTagNo(set.getString("tag_no"));
            instrument.setMake(set.getString("make"));
            instrument.setModel(set.getString("model"));
            instrument.setRemarks(set.getString("remarks"));
            instrument.setLocation(set.getString("location"));
            instrumentList.add(instrument);
        }
        return instrumentList;
    }
}
