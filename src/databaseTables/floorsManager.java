package databaseTables;

import java.sql.Connection;
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
}
