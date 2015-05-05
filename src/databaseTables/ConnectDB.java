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
                .getConnection(LocalConnectionString, "tud50428", "IJoovu9u");
    }

    /**
     * Tests if the connection is valid
     *
     * @param conn a JDBC connection object
     * @param dbVendor db vendor "mysql"
     * @return true if the connection object is valid and false if not
     * @throws Exception
     */
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

    /**
     * Tests if there is a connection to the database
     *
     * @param conn a JDBC connection object
     * @return true if a given connection object is valid and false if not
     */
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

    /**
     * Checks whether a user exists in the database
     *
     * @return true if user exists; Otherwise return false
     */
    public static boolean checkifuserexists() {
        ResultSet rs = null;
        Statement stmt = null;
        try {
            Connection conn = getConnection();
            stmt = conn.createStatement();
            if (stmt == null) {
                return false;
            }

            String username = LoginScreen.usernameField.getText();

            String password = new String(LoginScreen.passwordField.getPassword());

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
