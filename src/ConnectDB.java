
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
   
    private static final String UserName = ""; // Enter your temple accessname and password that Wolfgang provided in the card
    private static final String Password = "";
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
