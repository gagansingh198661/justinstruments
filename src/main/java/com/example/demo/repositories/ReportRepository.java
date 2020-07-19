package com.example.demo.repositories;

import com.example.demo.dtos.ReportDTO;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ReportRepository implements  Repository<ReportDTO>{

    @Override
    public List<ReportDTO> getAll() {
        List<ReportDTO> clintList=new LinkedList<>();
        try {
            Connection conn = ConnectionUtility.getConnection();
            String sql = "select * from report order by output";

            PreparedStatement statement=conn.prepareStatement(sql);
            ResultSet set=statement.executeQuery();
            while(set.next()){
                ReportDTO client = new ReportDTO();

                clintList.add(client);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return clintList;
    }

    @Override
    public List<ReportDTO> getDTO(int id) {
        return null;
    }

    @Override
    public List<ReportDTO> getDTO(String id) {
        return null;
    }

    public void save(ReportDTO report) throws SQLException, IOException {
        Connection con=null;
        FileInputStream stream=null;
        try {
            con = ConnectionUtility.getConnection();
            String sql = "INSERT INTO report (report,report_name,client_id,client_name,creation_date,expiry_date) " +
                    "values (?,?,?,?,?,?)";
            stream=new FileInputStream(report.getReportFile());
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setBlob(1, stream);
            statement.setString(2,report.getReportName());
            statement.setInt(3,report.getClientId());
            statement.setString(4,report.getClientName());
            statement.setDate(5,new Date(report.getReportCreationDate().getTime()));
            statement.setDate(6,new Date(report.getReportExpiryDate().getTime()));
            int k=statement.executeUpdate();

        }catch(Exception e){
            System.out.println(e);
        }finally {
            con.close();
            stream.close();
        }
    }

    public List<ReportDTO> getReports(){
        String func1="CURRENT_DATE()";
        String func2="ADDDATE(";
        String func3=", INTERVAL 1 MONTH)";

        String sqlStr="SELECT  * from report where expiry_date BETWEEN "+ func1+" and " +func2+func1+func3;
        Connection conn=ConnectionUtility.getConnection();
        List<ReportDTO> reportDTOList=null;
        try {
            PreparedStatement statement = conn.prepareStatement(sqlStr);
            ResultSet set = statement.executeQuery();
            reportDTOList=getReportList(set);
        }catch(Exception e){

        }
        return reportDTOList;
    }

    private List<ReportDTO> getReportList(ResultSet set) throws SQLException {
        List<ReportDTO> clientList=new LinkedList<>();
        while(set.next()){
            ReportDTO client = new ReportDTO();
            client.setReportName(set.getString("report_name"));
            client.setClientName(set.getString("client_name"));
            client.setReportExpiryDate(set.getDate("expiry_date"));
            client.setReportBlob(set.getBlob("report"));
            clientList.add(client);

        }
        return clientList;
    }
}
