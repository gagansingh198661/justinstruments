package com.example.demo.repositories;




import com.example.demo.entities.Output;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
@Component
public class OutputRepository implements Repository<Output> {

    @Override
    public List<Output> getAll() {
        List<Output> clintList=new LinkedList<>();
        try {
            Connection conn = ConnectionUtility.getConnection();
            String sql = "select * from output order by output";

            PreparedStatement statement=conn.prepareStatement(sql);
            ResultSet set=statement.executeQuery();
            while(set.next()){
                Output client = new Output();
                client.setOutput(Integer.valueOf(set.getString("output")));
                clintList.add(client);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return clintList;
    }

    @Override
    public List<Output> getDTO(int id) {
        return null;
    }

    @Override
    public List<Output> getDTO(String id) {
        return null;
    }
}
