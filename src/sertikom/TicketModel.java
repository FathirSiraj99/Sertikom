/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sertikom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class TicketModel {
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    public void showData(DefaultTableModel dtm) throws SQLException {
        try {
            conn = new Koneksi().connect();
            
            String sql = "SELECT * FROM tbl_transaksi";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
               
            Object[] data = {
                 rs.getString("id"),
                 rs.getString("idTiket"),
                 rs.getString("JB")
                 };
         
                dtm.addRow(data);  
            }
            rs.close();
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Pengunjungmodel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Pengunjungmodel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     
     public Integer getDataIdProdukByKode(String Ticket,TicketPage tp) 
            {
             
                 try {
            conn = new Koneksi().connect();
            
            String sql = "SELECT * FROM tbl_tiket WHERE Ticket=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, Ticket);
            rs = pst.executeQuery();
            Integer id = 0;
            
            
            if (rs.next()) {
               
                id = rs.getInt("id");
                tp.txtHarga.setText(rs.getString("Harga"));
                
            }
            
            tp.txtKuantitas.setText("1");
            
            
            rs.close();
            conn.close();
            
            return id;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Pengunjungmodel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(Pengunjungmodel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
     
     
     
     public void insertData(Integer idTiket,Integer JB) 
            {
                
        try {
            conn = new Koneksi().connect();
//            query untuk insert sesuaikan dengan kolom di table
            
            String sql = "INSERT INTO tbl_transaksi"
                    +" (idTiket,JB)"
                    + " VALUES(?,?)";
            
            pst = conn.prepareStatement(sql);
//             passing data sesuai tipe data di kolom table 
            pst.setInt(1, idTiket);
            pst.setInt(2, JB);
            
            
            
            
            
            pst.execute();
            JOptionPane.showMessageDialog(null,"Sukses membuat data");
            conn.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Pengunjungmodel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Pengunjungmodel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public void deleteData(Integer id){
        try {
            conn = new Koneksi().connect();
            
            String sql = "DELETE FROM tbl_transaksi"
                    + " WHERE Id=?";
            
            
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            
            JOptionPane.showMessageDialog(null,"Sukses delete data");
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Pengunjungmodel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Pengunjungmodel.class.getName()).log(Level.SEVERE, null, ex);
        }
      }

 
   
}
