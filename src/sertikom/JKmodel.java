/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sertikom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class JKmodel {
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    public void showData(DefaultTableModel dtm) throws SQLException {
        try {
            conn = new Koneksi().connect();
            
            String sql = "SELECT * FROM jeniskelamin";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
               
            Object[] data = {
                 rs.getString("id"),
                 rs.getString("Jenis")
                 };
         
                dtm.addRow(data);  
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Pengunjungmodel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Pengunjungmodel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<String> showDataForSelectionGroup() {
        
        try {
            conn = new Koneksi().connect();
            
            String sql = "SELECT * FROM jeniskelamin";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
    
//            inisialisasi list dari item yang akan ditampilkan
            List<String> listOfItems = new ArrayList<>();

//looping data            
            while (rs.next()) {
              //            inisialisasi list dari item yang akan ditampilkan

                listOfItems.add(rs.getString("Jenis"));
            }
            
            
            conn.close();
            
            return listOfItems;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Pengunjungmodel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(Pengunjungmodel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
