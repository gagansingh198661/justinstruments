package com.example.demo.repositories;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtility {



    private static String database;
    private static String server;
    private static String port;
    private static String username;
    private static String password;
    public static String getDatabase() {
        return database;
    }

    public static void setDatabase(String database) {
        ConnectionUtility.database = database;
    }

    public static String getServer() {
        return server;
    }

    public static void setServer(String server) {
        ConnectionUtility.server = server;
    }

    public static String getPort() {
        return port;
    }

    public static void setPort(String port) {
        ConnectionUtility.port = port;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        ConnectionUtility.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        ConnectionUtility.password = password;
    }

    public static Connection getConnection(){
        try {
            if(connection==null||connection.isClosed()){

                Class.forName("com.mysql.cj.jdbc.Driver");
                String connectionString="jdbc:mysql://localhost";
                connectionString+=":"+getPort();
                connectionString+="/"+getDatabase();
                connection= DriverManager.getConnection(connectionString,getUsername(),getPassword());
                return connection;

            }
        }catch(Exception e){
            System.out.println(e);
        }

        return connection;
    }

    public static boolean isConnectionTestOk(){
        try {
            boolean falg=getConnection().isClosed();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        System.out.println("Connection success");
        return true;
    }

    private static Connection connection;

    public static Properties getconnectionProperties(){
        try{
            System.out.println(ConnectionUtility.class.getProtectionDomain().getCodeSource().getLocation());
            File[] files= new File(ConnectionUtility.class.getProtectionDomain().getCodeSource().getLocation()
                    .toURI()).listFiles();

            File confiFile=null;
            for(File f:files){
                if(f.getName().equals("configFile.txt")) {
                    confiFile=f;
                    break;
                }
            }
            if(confiFile==null){
                throw  new FileNotFoundException("ConfigFile not found");
            }else{
                Properties gfg = new Properties();
                FileInputStream in = new FileInputStream(confiFile);
                gfg.load(in);
                return gfg;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
}
