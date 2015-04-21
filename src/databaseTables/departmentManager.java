package databaseTables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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
    public static department getColor(String roomNum, String floor, String building) throws ClassNotFoundException, SQLException {

        String sql = "SELECT FOAPAL_color from department d "
                + "inner join Rooms R on R.FOAPAL_code = d.FOAPAL_code "
                + "inner join building B on B.building_code = R.building_code "
                + "where room_num = '" + roomNum + "' and building_name = '" + building + "' and "
                + "floor_name = '" + floor + "'";
  department d = new department();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Scanner s = new Scanner(System.in);

        try {

            conn = ConnectDB.getConnection();// creating the connection
            pstmt = conn.prepareStatement(sql);// creating the statement that is already has its value

            rs = pstmt.executeQuery(); // excuting the statement

            if (rs.next()) {

                
              

                d.setFOAPAL_color(rs.getString("FOAPAL_color"));
                

                //return d;
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

        return d;
    }
}
