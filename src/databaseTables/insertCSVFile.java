/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseTables;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * Gets the spreadsheets add the data to the database
 */
public class insertCSVFile {

    /**
     *
     * @param csvFile a spreadsheet file with .csv extension
     * @throws SQLException if the columns in the file doesn't match with the
     * database tables
     * @throws ClassNotFoundException if there's no connection
     */
    public static void insertCSV(String csvFile) throws SQLException, ClassNotFoundException {

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(csvFile));

            if ((line = br.readLine()) != null) {
                String[] titles = line.split(cvsSplitBy);
                //title[0]= campus, buildingcode, buldingname, floor, roomnumber, foapal, foapalname, roomtype, description, roomarea, actiondate, actiontype;
                //Campus,Building #,Building Name,Floor ,4Room #,FOAPAL,FOAPAL Name,Room Type Description,Room Area (Square Feet),Action Date (MM/YY),Action Type (ADD/CHANGE)

                if (titles[0].equals("Campus") && titles[1].equals("Building #") && titles[2].equals("Building Name")
                        && titles[3].equals("Floor ") && titles[4].equals("Room #") && titles[5].equals("FOAPAL")
                        && titles[6].equals("FOAPAL Name") && titles[7].equals("Room Type Description")
                        && titles[8].equals("Room Area (Square Feet)") && titles[9].equals("Action Date (MM/YY)")
                        && titles[10].equals("Action Type (ADD/CHANGE)")) {

                } else {
                    JOptionPane.showMessageDialog(null, "The csv file you selected is not in correct format", "Error!", JOptionPane.OK_OPTION);
                    return;
                }
            }
            while ((line = br.readLine()) != null) {
                String[] titles = line.split(cvsSplitBy);
                String campusname = null;
                int floorcode = 0;

                switch (titles[0]) {
                    case "MC":
                        campusname = "Main Campus";
                        break;
                    case "HSC":
                        campusname = "Health Sciences Center";
                        break;
                    case "AC":
                        campusname = "Ambler Campus";
                        break;
                }

                switch (titles[3]) {
                    case "Ground Floor":
                        floorcode = 0;
                        break;
                    case "First Floor":
                        floorcode = 1;
                        break;
                    case "Second Floor":
                        floorcode = 2;
                        break;
                    case "Third Floor":
                        floorcode = 3;
                        break;
                    case "Fourth Floor":
                        floorcode = 4;
                        break;
                    case "Fifth Floor":
                        floorcode = 5;
                        break;
                    case "Sixth Floor":
                        floorcode = 6;
                        break;
                    case "Seventh Floor":
                        floorcode = 7;
                        break;
                    case "Eight Floor":
                        floorcode = 8;
                        break;
                    case "Ninth Floor":
                        floorcode = 9;
                        break;
                    case "Tenth Floor":
                        floorcode = 10;
                        break;
                }

                String campusQuery = "INSERT INTO campus (campus_code,campus_name) "
                        + "VALUES ('" + titles[0] + "','" + campusname + "') ON DUPLICATE KEY UPDATE campus_name= '" + campusname + "'";

                String buildingQuery = "INSERT INTO building (building_code,building_name,campus_code) "
                        + "VALUES ('" + titles[1] + "','" + titles[2] + "','" + titles[0] + "') ON DUPLICATE KEY UPDATE building_name=  '" + titles[2] + "'";

                String foapalQuery = "INSERT INTO department (FOAPAL_code,FOAPAL_name) "
                        + "VALUES ('" + titles[5] + "','" + titles[6] + "') ON DUPLICATE KEY UPDATE FOAPAL_name=  '" + titles[6] + "'";

                String floorQuery = "INSERT INTO floors (floor_code,floor_name,building_code) "
                        + "VALUES ('" + floorcode + "','" + titles[3] + "','" + titles[1] + "') ON DUPLICATE KEY UPDATE floor_name=  '" + titles[3] + "'";

                String roomQuery = "INSERT INTO Rooms (room_num,building_code,floor_name,FOAPAL_code,"
                        + "room_type_des,room_area_sqft,roomcolor, comments)"
                        + "VALUES ('" + titles[4] + "','" + titles[1] + "','" + titles[3] + "','" + titles[5] + "',"
                        + "'" + titles[7] + "','" + titles[8] + "','49F41E', '" + titles[9] + " " + titles[10] + "') ON DUPLICATE KEY UPDATE FOAPAL_code = '" + titles[5] + "', room_type_des=  '" + titles[7] + "', room_area_sqft = '" + titles[8] + "'";

                Connection conn = null;
                Statement stmt = null;

                conn = ConnectDB.getConnection();// creating the connection
                stmt = conn.createStatement();// creating the statement that is already has its value       
                stmt.addBatch(campusQuery); // excuting the statement
                stmt.addBatch(buildingQuery); // excuting the statement
                stmt.addBatch(foapalQuery); // excuting the statement
                stmt.addBatch(floorQuery); // excuting the statement
                stmt.addBatch(roomQuery); // excuting the statement
                stmt.executeBatch();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BatchUpdateException ex) {
            int[] updateCount = ex.getUpdateCounts();
            int count = 1;
            for (int i : updateCount) {
                if (i == Statement.EXECUTE_FAILED) {
                    System.out.println("Error on request " + count + ": Execute failed");
                } else {
                    System.out.println("Request " + count + ": OK");
                }
                count++;
            }
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Done");

    }

}
