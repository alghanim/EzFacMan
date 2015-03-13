package databaseTables;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RoomsManager {


    public static void displayAllRooms() throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM Rooms";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            conn = ConnectDB.getConnection();// creating the connection
            stmt = conn.createStatement();// creating the statement that is already has its value
            rs = stmt.executeQuery(sql); // excuting the statement

            while (rs.next()) {
                StringBuffer bf = new StringBuffer();
              
               // bf.append("Room Number: " + rs.getString("room_num") + "\n");
               Rooms R = new Rooms();
               ArrayList<String> list = new ArrayList();
               
               for(int i = 0; i < list.size(); i++){
               list.add(rs.getString("room_num"));
             //  R.setRoom_num(rs.getString("room_num"));
              
                   list.toString();
               }
                
                bf.append("---------------------");

                System.out.println(bf.toString());
            }
        } catch (SQLException ex) {
            System.err.println("Error Message: " + ex.getMessage());
            System.err.println("Error Code: " + ex.getErrorCode());
            System.err.println("SQL State: " + ex.getSQLState());
        }
    }
}


