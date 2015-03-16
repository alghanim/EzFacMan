package databaseTables;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class building {

    private String building_name;
    private int building_code;
    private String campus_name;

    /**
     *
     * @return Building name
     */
    public String getBuilding_name() {
        return building_name;
    }

    /**
     * set the building name to the database
     *
     * @param building_name
     */
    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }

    /**
     *
     * @return Building Code
     */
    public int getBuilding_code() {
        return building_code;
    }

    /**
     * Set the building Code to the database
     *
     * @param building_code
     */
    public void setBuilding_code(int building_code) {
        this.building_code = building_code;
    }

    /**
     *
     * @return Campus name
     */
    public String getCampus_name() {
        return campus_name;
    }

    /**
     * set the campus name to the database
     *
     * @param campus_name
     */
    public void setCampus_name(String campus_name) {
        this.campus_name = campus_name;
    }

}
