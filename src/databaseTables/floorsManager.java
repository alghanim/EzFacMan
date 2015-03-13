package databaseTables;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ali
 */
public class floorsManager {
     
    public static void displayAllfloors() throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM floors";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            conn = ConnectDB.getConnection();// creating the connection
            stmt = conn.createStatement();// creating the statement that is already has its value
            rs = stmt.executeQuery(sql); // excuting the statement

            while (rs.next()) {
                StringBuffer bf = new StringBuffer();

                bf.append("Floor name: " + rs.getString("floor_name") + "\n");
                bf.append("Floor Code: " + rs.getInt("floor_code") + "\n");
                bf.append("Building Code: " + rs.getInt("building_code") + "\n");

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
