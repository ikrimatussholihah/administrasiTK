package koneksi;
import java.sql.*;
public class koneksi {
public Connection koneksi;
public Connection connect(){
try{
Class.forName("com.mysql.jdbc.Driver");
System.out.println("berhasil konek");
}
catch(ClassNotFoundException ex){
System.out.println("gagal koneksi"+ex);
}
String url = "jdbc:mysql://localhost/administrasi";
try{
koneksi = DriverManager.getConnection(url,"root","1010");
System.out.println("berhasil koneksi database");
}
catch (SQLException ex){
System.out.println("gagal koneksi database"+ex);
}
return koneksi;
}
}