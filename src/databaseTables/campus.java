package databaseTables;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
     * @param campus_name
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
     * @param campus_code
     */
    public void setCampus_code(String campus_code) {
        this.campus_code = campus_code;
    }

}
