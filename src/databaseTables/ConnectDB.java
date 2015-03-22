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

/**
 * Connect the application to the database by authenticating the user
 *
 * @author Ali
 */
public class ConnectDB {

    private static final String ConnectionString
            = "jdbc:mysql://babyhuey.cis.temple.edu:3306/team4";

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
                .getConnection("jdbc:mysql://localhost:1234/team4", LoginScreen.usernameField.getText(), LoginScreen.passwordField.getText());
    }
}
