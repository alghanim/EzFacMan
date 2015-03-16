package databaseTables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Manage SQL statements to retrieve data from department table
 *
 * @author Ali
 */
public class departmentManager {

    /**
     * Display all rows and columns from Departments table
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void displayAllDepartments() throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM department";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            conn = ConnectDB.getConnection();// creating the connection
            stmt = conn.createStatement();// creating the statement that is already has its value
            rs = stmt.executeQuery(sql); // excuting the statement

            while (rs.next()) {
                StringBuffer bf = new StringBuffer();

                bf.append("Department name: " + rs.getString("FOAPAL_name") + "\n");
                bf.append("Department code: " + rs.getString("FOAPAL_color") + "\n");
                bf.append("Department color:" + rs.getLong("FOAPAL_code") + "\n");

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
