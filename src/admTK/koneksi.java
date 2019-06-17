/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admTK;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
/**
 *
 * @author Lenovo
 */
public class koneksi {
    Connection con;
    public koneksi() {
        String id, pass, driver, url;
        id= "root";
        pass = "1010";
        driver = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost/administrasi";
        try{
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url,id,pass);
            if (con==null){
                JOptionPane.showMessageDialog (null,"KONEKSI DATABASE GAGAL");
            }
        } catch (Exception e){
            System.out.println(""+e.getMessage());
        }
    }
    
public static void main (String[]args){
    koneksi k=new koneksi();
}

    Connection connect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}