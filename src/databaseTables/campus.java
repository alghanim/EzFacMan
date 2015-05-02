package databaseTables;

import java.util.ArrayList;

/**
 * Generate the getters and setters from Campus table
 *
 * @author Ali
 */
public class campus {

    private String campus_name;
    private String campus_code;
    private ArrayList<String> allcampuses = new ArrayList<String>();

    public ArrayList<String> getAllcampuses() {
        return allcampuses;
    }

    public void setAllcampuses(ArrayList<String> allcampuses) {
        this.allcampuses = allcampuses;
    }

    /**
     *
     * @return Campus name
     */
    public String getCampus_name() {
        return campus_name;
    }

    /**
     * set the campus name for the database
     *
     * @param campus_name has the campus name 
     */
    public void setCampus_name(String campus_name) {
        this.campus_name = campus_name;
    }

    /**
     *
     * @return Campus code
     */
    public String getCampus_code() {
        return campus_code;
    }

    /**
     * Set the campus code for the database
     *
     * @param campus_code the campus number
     */
    public void setCampus_code(String campus_code) {
        this.campus_code = campus_code;
    }

}
