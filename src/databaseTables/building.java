package databaseTables;

import java.util.ArrayList;

/**
 * Generate the getters and setters of building table
 *
 * @author Ali
 */
public class building {

    private String building_name;
    private int building_code;
    private String campus_name;
    private ArrayList allbuildings = new ArrayList<>();

    /**
     * gets all buildings from building table
     *
     * @return all buildings
     */
    public ArrayList getAllbuildings() {
        return allbuildings;
    }

    /**
     * set all buildings to call them
     *
     * @param allbuildings list has all buildings stored in the database
     */
    public void setAllbuildings(ArrayList allbuildings) {
        this.allbuildings = allbuildings;
    }

    /**
     * get building name
     *
     * @return Building name
     */
    public String getBuilding_name() {
        return building_name;
    }

    /**
     * set the building name to the database
     *
     * @param building_name takes the name of the building from the Database and
     * store it in building class
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
     * @param building_code has the selected building number
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
     * @param campus_name has the campus name of the selected room
     */
    public void setCampus_name(String campus_name) {
        this.campus_name = campus_name;
    }

}
