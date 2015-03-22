package databaseTables;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ali
 */
public class floors {

    private String floor_name;
    private int building_code;
    private int floor_code;
    private ArrayList<String> allFloors = new ArrayList<String>();

    public ArrayList<String> getAllFloors() {
        return allFloors;
    }

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
     * @param floor_name
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
     * @param building_code
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
     * @param floor_code
     */
    public void setFloor_code(int floor_code) {
        this.floor_code = floor_code;
    }

}
