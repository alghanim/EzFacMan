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
     * @param room
     * @param building
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Rooms displayCertainRooms(String room, String building) throws SQLException, ClassNotFoundException {

        String sql = "select Rooms.building_code,Rooms.room_num,Rooms.FOAPAL_code,room_type_des,\n"
                + "room_area_sqft,comments,floor_name, b.building_name, department.FOAPAL_name from Rooms\n"
                + "inner join department on Rooms.FOAPAL_code = department.FOAPAL_code\n"
                + "inner join building b on Rooms.building_code = b.building_code\n"
                + "where room_num = '" + room + "' and b.building_name = '" + building + "'";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Scanner s = new Scanner(System.in);
     

        try {

            conn = ConnectDB.getConnection();// creating the connection
            pstmt = conn.prepareStatement(sql);// creating the statement that is already has its value
           
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
                R.setFloor_name(rs.getString("floor_name"));
                R.setBuilding_name(rs.getString("building_name"));

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
