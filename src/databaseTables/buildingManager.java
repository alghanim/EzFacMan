package databaseTables;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Ali
 */
public class buildingManager {

    /**
     * Display all the buildings that are stored in the database under building
     * table
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static building displayAllBuildings() throws SQLException, ClassNotFoundException {

        building b = new building();
        String sql = "select distinct building_name from building";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            conn = ConnectDB.getConnection();// creating the connection
            stmt = conn.createStatement();// creating the statement that is already has its value
            rs = stmt.executeQuery(sql); // excuting the statement
            ArrayList list = new ArrayList<String>();
            while (rs.next()) {

                list.add(rs.getString("building_name"));

            }
            b.setAllbuildings(list);
        } catch (SQLException ex) {
            System.err.println("Error Message: " + ex.getMessage());
            System.err.println("Error Code: " + ex.getErrorCode());
            System.err.println("SQL State: " + ex.getSQLState());
        }
        return b;
    }

    public static building display(String dCampus) throws ClassNotFoundException {
        String sql;
        building b = new building();
        if (dCampus != null) {
            sql = "select distinct building_name from building where campus_code = 'MC'";
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

                list.add(rs.getString("building_name"));

            }
            b.setAllbuildings(list);
        } catch (SQLException ex) {
            System.err.println("Error Message: " + ex.getMessage());
            System.err.println("Error Code: " + ex.getErrorCode());
            System.err.println("SQL State: " + ex.getSQLState());
        }
        return b;

    }
}
