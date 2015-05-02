package databaseTables;

import java.util.ArrayList;

/**
 * Genereate the getters and setters of the floors table
 * @author Ali
 */
public class floors {

    private String floor_name;
    private int building_code;
    private int floor_code;
    private ArrayList<String> allFloors = new ArrayList<String>();
/**
 * get all floors from floors table
 * @return all floors
 */
    public ArrayList<String> getAllFloors() {
        return allFloors;
    }
/**
 * set all floors to call them
 * @param allFloors contains all floors in floor table
 */
    public void setAllFloors(ArrayList<String> allFloors) {
        this.allFloors = allFloors;
    }

    /**
     *
     * @return Floor name
     */
    public String getFloor_name() {
        return floor_name;
    }

    /**
     * set the floor name to the database
     *
     * @param floor_name contains floor name
     */
    public void setFloor_name(String floor_name) {
        this.floor_name = floor_name;
    }

    /**
     *
     * @return Building Code
     */
    public int getBuilding_code() {
        return building_code;
    }

    /**
     * set the building code to the database
     *
     * @param building_code contains building number
     */
    public void setBuilding_code(int building_code) {
        this.building_code = building_code;
    }

    /**
     *
     * @return floor code
     */
    public int getFloor_code() {
        return floor_code;
    }

    /**
     * set the floor code to the database
     *
     * @param floor_code contains floor number
     */
    public void setFloor_code(int floor_code) {
        this.floor_code = floor_code;
    }

}
