package com.example.demo.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class ApplicationRepository implements Repository<Properties> {
    @Override
    public List<Properties> getAll() {
        List<Properties> clintList=new LinkedList<>();
        try {
            Connection conn = ConnectionUtility.getConnection();
            String sql = "select * from application_property";

            PreparedStatement statement=conn.prepareStatement(sql);
            ResultSet set=statement.executeQuery();
            clintList=getClientList(set);
        }catch(Exception e){

        }
        return clintList;
    }

    @Override
    public List<Properties> getDTO(int id) {
        return null;
    }

    @Override
    public List<Properties> getDTO(String id) {
        return null;
    }

    private List<Properties> getClientList(ResultSet set) throws SQLException {
        List<Properties> clientList=new LinkedList<>();
        Properties client = new Properties();
        while(set.next()){
            String key=set.getString("property_key");
            String value=set.getString("property_value");
            client.put(key,value);
        }
        clientList.add(client);
        return clientList;
    }
}
