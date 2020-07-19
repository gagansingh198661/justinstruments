package com.example.demo.repositories;




import com.example.demo.entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ClientRepository implements Repository<Client>{
    public List<Client> getAllclients(){
        List<Client> clintList=new LinkedList<>();
        try {
            Connection conn = ConnectionUtility.getConnection();
            String sql = "select * from client";

            PreparedStatement statement=conn.prepareStatement(sql);
            ResultSet set=statement.executeQuery();
            clintList=getClientList(set);
        }catch(Exception e){

        }
        return clintList;
    }

    @Override
    public List<Client> getAll() {
        List<Client> clintList=new LinkedList<>();
        try {
            Connection conn = ConnectionUtility.getConnection();
            String sql = "select * from client";
            PreparedStatement statement=conn.prepareStatement(sql);
            ResultSet set=statement.executeQuery();
            clintList=getClientList(set);
        }catch(Exception e){

        }
        return clintList;
    }


    private List<Client> getClientList(ResultSet set) throws SQLException {
        List<Client> clientList=new LinkedList<>();
        while(set.next()){
            Client client = new Client();
            client.setAddress(set.getString("address"));
            client.setName(set.getString("name"));
            client.setPhone(set.getString("phone"));
            client.setFax(set.getString("fax"));
            clientList.add(client);

        }
        return clientList;
    }

    @Override
    public List<Client> getDTO(int id) {
        return null;
    }

    @Override
    public List<Client> getDTO(String id)  {
        List<Client> clintList=new LinkedList<>();
        Connection conn=null;
        try {
            conn = ConnectionUtility.getConnection();
            String sql = "select * from client where name='"+id+"'";
            PreparedStatement statement=conn.prepareStatement(sql);
            ResultSet set=statement.executeQuery();
            clintList=getClientList(set);
        }catch(Exception e){

        }finally {
           try {
               conn.close();
           }catch(Exception e){
               e.printStackTrace();
           }
        }
        return clintList;
    }
}
