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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class Pengunjungmodel {
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    public void showData(DefaultTableModel dtm) throws SQLException{
        try {
            conn = new Koneksi().connect();
           
            String sql = "SELECT * FROM tbl_pengunjung";
            pst = conn.prepareStatement(sql);
            rs  = pst.executeQuery();
           
            while (rs.next()){
                Object[] data = {
                rs.getString("id"),
                rs.getString("Nama"),
                rs.getString("Jk"),
                rs.getString("Alamat"),
                rs.getString("Usia"),
                rs.getString("Hp"),
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
            
            String sql = "SELECT * FROM tbl_pengunjung";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
    
            List<String> listOfItems = new ArrayList<>();
            
            while (rs.next()) {
                listOfItems.add(rs.getString("Jk"));
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
    
     public void insertData(String kodeProduk,
            String Nama, String Jk, String Alamat , Integer Usia , Integer Hp) 
            {
                
        try {
            conn = new Koneksi().connect();
//            query untuk insert sesuaikan dengan kolom di table
            
            String sql = "INSERT INTO tbl_pengunjung"
                    +" (Nama,Jk,Alamat,Usia,Hp)"
                    + " VALUES(?,?,?,?,?)";
            
            pst = conn.prepareStatement(sql);
//             passing data sesuai tipe data di kolom table 
            pst.setString(1, Nama);
            pst.setString(2, Jk);
            pst.setString(3, Alamat);
            pst.setInt(4, Usia);
            pst.setInt(5, Hp);
            
            //  untuk insert,update dan delete gunakan perintah execute
            pst.execute();
            JOptionPane.showMessageDialog(null,"Sukses membuat data");
            conn.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Pengunjungmodel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Pengunjungmodel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void updateData(Integer id,String Nama,
            String Jk, String Alamat, Integer Usia , Integer Hp) {
        try {
            conn = new Koneksi().connect();
            String sql = "UPDATE tbl_pengunjung SET Nama=?,"
                    + "Jk=?,"
                    + "Alamat=?,"
                    + "Usia=?,"
                    + "Hp=?"
                    + " WHERE id=?";
            
            System.out.println(sql);
            pst = conn.prepareStatement(sql);
            pst.setString(1, Nama);
            pst.setString(2, Jk);
            pst.setString(3, Alamat);
            pst.setInt(4, Usia);
            pst.setInt(5, Hp);
            pst.setInt(6, id);
            
            
            pst.execute();
            
            JOptionPane.showMessageDialog(null,"Sukses mengedit data");
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
            
            String sql = "DELETE FROM tbl_pengunjung"
                    + " WHERE id=?";
            
            
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
      
    public void searchData(DefaultTableModel dtm,String cari) {
        try {
            conn = new Koneksi().connect();
            //    searching data berdasarkan nama produk atau kategori produk
            String sql = "SELECT * FROM tbl_pengunjung WHERE NamaProduk LIKE ? OR "
                    + "KategoriProduk LIKE ? ";
            pst = conn.prepareStatement(sql);
            //    gunakan percent sebelum variable untuk mencari nama
            pst.setString(1, "%"+cari+"%");
            pst.setString(2, "%"+cari+"%");
            
            rs = pst.executeQuery();
//             looping data
            while (rs.next()) {
               
            Object[] data = {
                 rs.getString("id"),
                 rs.getString("KodeProduk"),
                 rs.getString("NamaProduk"),
                 rs.getString("KategoriProduk"),
                 rs.getString("HargaProduk"),
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

    
    
    
}
