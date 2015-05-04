package databaseTables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manage SQL statements to get the data from Campus table
 *
 * @author Ali
 */
public class campusManager {

    /**
     *  
     * @return all rows and columns from Campus table
     * @throws SQLException if there is not campuses
     * @throws ClassNotFoundException if the campus class is not found
     */
    public static campus displayAllCampuses() throws SQLException, ClassNotFoundException {
        campus c = new campus();

        String sql = "select distinct campus_code from campus";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            conn = ConnectDB.getConnection();// creating the connection
            stmt = conn.createStatement();// creating the statement that is already has its value
            rs = stmt.executeQuery(sql); // excuting the statement

            ArrayList list = new ArrayList<>();
            while (rs.next()) {

                list.add(rs.getString("campus_code"));

            }
            c.setAllcampuses(list);
        } catch (SQLException ex) {
            System.err.println("Error Message: " + ex.getMessage());
            System.err.println("Error Code: " + ex.getErrorCode());
            System.err.println("SQL State: " + ex.getSQLState());
        }
        return c;
    }

}
