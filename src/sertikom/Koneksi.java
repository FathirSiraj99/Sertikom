/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sertikom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class Koneksi {
    private final String JDBC_URL ="com.mysql.cj.jdbc.Driver"; 
    private final String DB_NAME = "db_tiket";
    private final String DB_URL = "jdbc:mysql://localhost/"+ DB_NAME;
    private final String USER = "root";
    private final String PASS = "";
    
    Connection conn;
    
    public Connection connect() throws ClassNotFoundException,
            SQLException{
        Class.forName(JDBC_URL);
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
            
        System.out.println("koneksi sukses");
        return conn;
    }
}
