package databaseTables;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Manage SQL statements to retrieve data from Floors table
 *
 * @author Ali
 */
public class floorsManager {

    public floorsManager() {
    }

    /**
     * Display all rows and columns from floors table
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static floors displayAllfloors() throws SQLException, ClassNotFoundException {
        floors f = new floors();
        String sql = "select distinct floor_name, floor_code from floors\n"
                + "order by floor_code;";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            conn = ConnectDB.getConnection();// creating the connection
            stmt = conn.createStatement();// creating the statement that is already has its value
            rs = stmt.executeQuery(sql); // excuting the statement
            ArrayList list = new ArrayList<String>();
            while (rs.next()) {

                list.add(rs.getString("floor_name"));

            }
            f.setAllFloors(list);
        } catch (SQLException ex) {
            System.err.println("Error Message: " + ex.getMessage());
            System.err.println("Error Code: " + ex.getErrorCode());
            System.err.println("SQL State: " + ex.getSQLState());
        }
        return f;
    }

    public void insertMap(String filename, String buildingName, String floorName) throws SQLException, ClassNotFoundException, FileNotFoundException {

        int len;
        String query = "update floors f inner join building b on b.building_code = f.building_code "
                + " set floor_map = ? "
                + "where b.building_name = '" + buildingName + "'"
                + " and floor_name = '" + floorName + "'";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectDB.getConnection();
            File file = new File(filename);
            FileInputStream fis = new FileInputStream(file);
            len = (int) file.length();

            pstmt = conn.prepareStatement(query);

            //method to insert a stream of bytes
            pstmt.setBinaryStream(1, fis, len);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getPDFData(String buildingName, String floorName) {

        byte[] fileBytes;
        String query;
        Connection conn = null;
        try {
            query
                    = "select floor_map from floors f \n"
                    + "inner join building b on\n"
                    + "b.building_code =  f.building_code\n"
                    + "where b.building_name = '" + buildingName + "' and floor_name = '" + floorName + "'";

            conn = ConnectDB.getConnection();
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(query);
            if (rs.next()) {
                fileBytes = rs.getBytes(1);
                OutputStream targetFile = new FileOutputStream(
                        "/Users/Ali/Desktop/newtest38.pdf");
                targetFile.write(fileBytes);
                targetFile.close();
            }

        } catch (SQLException | ClassNotFoundException | IOException e) {
        }
    }

    public static floors display(String sqlString) throws ClassNotFoundException {
        String sql;
        floors f = new floors();
        if (sqlString != null) {
            sql = "select floor_name from floors f\n"
                    + "inner join building b\n"
                    + "on f.building_code = b.building_code\n"
                    + "where b.building_name = '" + sqlString + "'";
        } else {

            sql = "select distinct building_name from building";
        }
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            conn = ConnectDB.getConnection();// creating the connection
            stmt = conn.createStatement();// creating the statement that is already has its value
            rs = stmt.executeQuery(sql); // excuting the statement
            ArrayList list = new ArrayList<String>();
            while (rs.next()) {

                list.add(rs.getString("floor_name"));

            }
            f.setAllFloors(list);
        } catch (SQLException ex) {
            System.err.println("Error Message: " + ex.getMessage());
            System.err.println("Error Code: " + ex.getErrorCode());
            System.err.println("SQL State: " + ex.getSQLState());
        }
        return f;
    }
}
