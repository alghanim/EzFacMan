package databaseTables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Manages SQL statements to retrieve the data from the database
 *
 * @author Ali
 */
public class RoomsManager {

    /**
     * Display the All rows and columns in Rooms table from the database
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Rooms displayCertainRooms() throws SQLException, ClassNotFoundException {

        String sql = "select building_code,room_num,Rooms.FOAPAL_code,room_type_des,"
                    + "room_area_sqft,comments, department.FOAPAL_name from Rooms "
                    + "inner join department on Rooms.FOAPAL_code = department.FOAPAL_code"
                + " where room_num = 'P102A' and building_code = 48";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Scanner s = new Scanner(System.in);
       // String rNumber = s.next();
       // int bCode = s.nextInt();

        try {

            conn = ConnectDB.getConnection();// creating the connection
            pstmt = conn.prepareStatement(sql);// creating the statement that is already has its value
         //   pstmt.setString(1, rNumber);
         //   pstmt.setInt(2, bCode);
            rs = pstmt.executeQuery(); // excuting the statement

            if (rs.next()) {

                Rooms R = new Rooms();
                department d = new department();

                R.setRoom_num(rs.getString("room_num"));
                R.setRoom_area_sqft(rs.getInt("room_area_sqft"));
                R.setBuilding_code(rs.getInt("building_code"));
                R.setFOAPAL_code(rs.getLong("FOAPAL_code"));
                R.setRoom_type_des(rs.getString("room_type_des"));
                R.setFOAPAL_name(rs.getString("FOAPAL_name"));

               

                return R;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }
}
