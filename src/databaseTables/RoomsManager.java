package databaseTables;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                + "room_area_sqft, roomcolor, comments,floor_name, b.building_name, department.FOAPAL_name from Rooms\n"
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
                R.setRoom_color(rs.getString("roomcolor"));
                R.setComments(rs.getString("comments"));
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
            conn.close();
        }
    }

    public static int buildingNametoCode(String buildingName) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT building_code FROM team4.building where building_name= '" + buildingName + "'";
            Scanner s = new Scanner(System.in);

            conn = ConnectDB.getConnection();// creating the connection
            pstmt = conn.prepareStatement(sql);// creating the statement that is already has its value

            rs = pstmt.executeQuery(); // excuting the statement

            if (rs.next()) {

                return rs.getInt("building_code");
            } else {
                return 0;
            }

        } catch (SQLException ex) {
            Logger.getLogger(RoomsManager.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RoomsManager.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            if (rs != null) {
                rs.close();
            }
            conn.close();
        }
    }
/**
 * update Room information 
 * @param roomNum
 * @param buildingName
 * @param departmentCode
 * @param roomType
 * @param roomArea
 * @throws SQLException
 * @throws ClassNotFoundException 
 */
    public static void updateRoomInfo(String roomNum,
            String buildingName, String departmentCode,
            String roomType, String roomArea) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE `team4`.`Rooms` SET `FOAPAL_code`='" + departmentCode + "', "
                + "`room_type_des`='" + roomType + "', `room_area_sqft`='" + roomArea + "' "
                + "WHERE `room_num`='" + roomNum + "' and`building_code`='" + buildingNametoCode(buildingName) + "'";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectDB.getConnection();// creating the connection
            pstmt = conn.prepareStatement(sql);// creating the statement that is already has its value
            pstmt.executeUpdate(); // excuting the statement
        } catch (SQLException e) {
            System.err.println(e);
        }

    }

    public static void addRoomInfo(String roomNum,
            int buildingCode, String floorName, String departmentCode,
            String departmentName, String roomType, String roomArea, String color, String commentBox) throws SQLException, ClassNotFoundException {

        //=String sql = "INSERT INTO "
        String foapalQuery = "INSERT INTO department (FOAPAL_code,FOAPAL_name) "
                + "VALUES ('" + departmentCode + "','" + departmentName + "') ON DUPLICATE KEY UPDATE FOAPAL_name =  '" + departmentName + "'";

        String roomQuery = "INSERT INTO Rooms (room_num,building_code,floor_name,FOAPAL_code,"
                + "room_type_des,room_area_sqft,roomcolor,comments)"
                + "VALUES ('" + roomNum + "','" + buildingCode + "','" + floorName + "','" + departmentCode + "',"
                + "'" + roomType + "','" + roomArea + "','" + color + "','" + commentBox + "') ON DUPLICATE KEY UPDATE room_type_des = '" + roomType + "', room_area_sqft = '" + roomArea + "', roomcolor = '" + color + "',  comments = '" + commentBox + "'";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        conn = ConnectDB.getConnection();// creating the connection
        stmt = conn.createStatement();// creating the statement that is already has its value       
        stmt.addBatch(foapalQuery); // excuting the statement
        stmt.addBatch(roomQuery); // excuting the statement
        stmt.executeBatch();
        conn.close();
    }

    public static Rooms getRoomColor(String roomNum, String building) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            int building_code = buildingNametoCode(building);
            String sql = "SELECT roomcolor from Rooms where room_num = '" + roomNum + "' and building_code = '" + building_code + "'";
            Rooms r = new Rooms();

            Scanner s = new Scanner(System.in);

            conn = ConnectDB.getConnection();// creating the connection
            pstmt = conn.prepareStatement(sql);// creating the statement that is already has its value

            rs = pstmt.executeQuery(); // excuting the statement

            if (rs.next()) {
                r.setRoom_color(rs.getString("roomcolor"));
            } else {
                return null;
            }
            return r;
        } catch (SQLException ex) {

            Logger.getLogger(RoomsManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RoomsManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            conn.close();
        }

    }

}
