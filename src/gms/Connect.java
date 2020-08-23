package gms;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

class Connect{
    static Connection con=null;
public static Connection toMysql(){

    try{
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver Loaded");

        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/GMS", "root","shiv");
        System.out.println("Connection Esatablished");
}catch(Exception e){
    JOptionPane.showMessageDialog(null,e);
}    
    return con;
}
    
}