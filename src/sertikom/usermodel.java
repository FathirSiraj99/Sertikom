/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sertikom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class usermodel {
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    public void login(String username,String pass , LoginPage lp) 
            throws ClassNotFoundException, SQLException{
        conn = new Koneksi().connect();
        
        Dashboard db = new Dashboard();
       
       
        String query = "SELECT * FROM tbl_admin "
                + "WHERE username=? and password=?";
        
        pst = conn.prepareStatement(query);
        pst.setString(1, username);
        pst.setString(2, pass);
        
        rs = pst.executeQuery();
        if(rs.next()){
            System.out.println("Login berhasil");
            JOptionPane.showMessageDialog(null, "Login berhasil");
            System.out.println("username:"+
                    rs.getString("username"));
            System.out.println("password:"+
                    rs.getString("password"));
            lp.dispose();
            db.setVisible(true);
            db.labelNama.setText(rs.getString("nama"));
            db.LabelAlamaty.setText(rs.getString("alamat"));
           
           
             
                
            
        }else{
              JOptionPane.showMessageDialog(null, "Login gagal"); 
            System.out.println("Login gagal , data tidak ada");
        }
        
}

    void login(String text, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
