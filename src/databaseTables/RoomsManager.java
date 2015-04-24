package databaseTables;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public static void updateRoomInfo(String roomNum, String floorName,
            String buildingName, String departmentCode,
            String departmentName, String roomType, String roomArea) throws SQLException, ClassNotFoundException {

        String sql = "update Rooms set room_num = '" + roomNum + "', floor_name ='" + floorName + "'";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        conn = ConnectDB.getConnection();// creating the connection
        stmt = conn.createStatement();// creating the statement that is already has its value
        rs = stmt.executeQuery(sql); // excuting the statement

    }

    public static void addRoomInfo(String roomNum, String floorName,
            String buildingName, String departmentCode,
            String departmentName, String roomType, String roomArea) throws SQLException, ClassNotFoundException {

        String sql = "insert into Rooms set room_num = '" + roomNum + "', floor_name ='" + floorName + "'";
/*String roomQuery = "INSERT INTO Rooms (room_num,building_code,floor_name,FOAPAL_code,"
                        + "room_type_des,room_area_sqft,comments)"
                        + "VALUES ('" + titles[4] + "','" + titles[1] + "','" + titles[3] + "','" + titles[5] + "',"
                        + "'" + titles[7] + "','" + titles[8] + "','" + titles[9] + titles[10] + "') ON DUPLICATE KEY UPDATE FOAPAL_code = '" + titles[5] + "' room_type_des=  '" + titles[7] + "' room_area_sqft = '" + titles[8] + "';";
*/
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        conn = ConnectDB.getConnection();// creating the connection
        stmt = conn.createStatement();// creating the statement that is already has its value
        rs = stmt.executeQuery(sql); // excuting the statement

    }
}
