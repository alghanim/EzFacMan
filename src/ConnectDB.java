/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ali
 */
public class ConnectDB {
    private static final String UserName = "tud16508";
    private static final String Password = "auze3Eih";
    private static final String ConnectionString = 
            "jdbc:mysql://babyhuey.cis.temple.edu:3306/team4";
   
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException
   
    {
        
        
        Class.forName("com.mysql.jdbc.Driver");
      // Setup the connection with the DB
      return DriverManager
          .getConnection(ConnectionString
              , UserName,Password);
    }
}
