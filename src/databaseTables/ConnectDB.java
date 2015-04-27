package databaseTables;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import EzFacMan.*;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Connect the application to the database by authenticating the user
 *
 * @author Ali
 */
public class ConnectDB {

    private static final String ConnectionString
            = "jdbc:mysql://babyhuey.cis.temple.edu:3306/team4";
    private static final String LocalConnectionString
            = "jdbc:mysql://localhost:1234/team4";

    /**
     *
     * @return a Connection using the Driver Manager using the local host , user
     * name and password
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager
                .getConnection(ConnectionString, "tud50428", "IJoovu9u");
    }
    
    public static boolean isValidConnection(Connection conn, String dbVendor) throws Exception {

        if (conn == null) {
            return false;
        }

        if (conn.isClosed()) {
            return false;
        }
        if (dbVendor.equalsIgnoreCase("mysql")) {
            return testConnection(conn);
        } else {
            return false;
        }

    }

    public static boolean testConnection(Connection conn) {
        ResultSet rs = null;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            if (stmt == null) {
                return false;
            }

            rs = stmt.executeQuery("select 1");
            if (rs == null) {
                return false;
            }

            // connection object is valid: you were able to
            // connect to the database and return something useful.
            if (rs.next()) {
                return true;
            }

            // there is no hope any more for the validity
            // of the Connection object
            return false;
        } catch (Exception e) {
            // something went wrong: connection is bad
            return false;
        } finally {
            // close database resources
            try {
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean checkifuserexists() {
        ResultSet rs = null;
        Statement stmt = null;
        try {
            Connection conn = getConnection();
            stmt = conn.createStatement();
            if (stmt == null) {
                return false;
            }
            
            //String username = LoginScreen.usernameField.getText();
            //String username = "tud50428";
            String username = "tuc69409";
            String password = "Pae9Cahz";
            //String password = new String(LoginScreen.passwordField.getPassword());
            //String password = "IJoovu9u";

            rs = stmt.executeQuery("SELECT accessnet FROM team4.users WHERE accessnet = '" + username + "' and password = '" + password + "'");
            if (rs == null) {
                return false;
            }

            // connection object is valid: you were able to
            // connect to the database and return something useful.
            if (rs.next()) {
                return true;
            }

            // there is no hope any more for the validity
            // of the Connection object
            return false;
        } catch (Exception e) {
            // something went wrong: connection is bad
            return false;
        } finally {
            // close database resources
            try {
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
