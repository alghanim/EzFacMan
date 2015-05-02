package databaseTables;

/**
 * generate the getters and setters for Rooms table
 * @author Ali
 */
public class Rooms {

    private String room_num;
    private int building_code;
    private String floor_name;
    private Long FOAPAL_code;
    private String room_type_des;
    private int room_area_sqft;
    private String FOAPAL_name;
    private String building_name;
    private String room_color;
    private String comment_box;

    /**
     * Gets the room color
     * @return room color
     */
    public String getRoom_color() {
        return room_color;
    }

    /**
     * Sets the room color for the clicked room
     * @param room_color contains room color
     */
    public void setRoom_color(String room_color) {
        this.room_color = room_color;
    }

    /**
     * return the comments for the selected room
     * @return comment
     */
    public String getComments() {
        return comment_box;
    }

    /**
     * Set the comment for the selected room
     * @param comment_box contains comment for the room selected
     */
    public void setComments(String comment_box) {
        this.comment_box = comment_box;
    }

    /**
     * get the building for the selected room
     * @return building name
     */
    public String getBuilding_name() {
        return building_name;
    }

    /**
     * sets building Name
     * @param building_name contains building name
     */
    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }

    /**
     * gets the department name
     * @return department name
     */
    public String getFOAPAL_name() {
        return FOAPAL_name;
    }

    /**
     * sets the department name
     * @param FOAPAL_name contains department name
     */
    public void setFOAPAL_name(String FOAPAL_name) {
        this.FOAPAL_name = FOAPAL_name;
    }

    /**
     * get the Room number
     *
     * @return the room number for the user
     */
    public String getRoom_num() {
        return room_num;
    }

    /**
     *
     * @param room_num send the room number to the database
     *
     */
    public void setRoom_num(String room_num) {
        this.room_num = room_num;
    }

    /**
     * gets the building code
     *
     * @return the building code
     */
    public int getBuilding_code() {
        return building_code;
    }

    /**
     * Set the building code and send it to the database
     *
     * @param building_code contains building number
     */
    public void setBuilding_code(int building_code) {
        this.building_code = building_code;
    }

    /**
     *
     * @return the Floor name
     *
     */
    public String getFloor_name() {
        return floor_name;
    }

    /**
     * it takes the floor name and send it to the database
     *
     * @param floor_name contains floor name
     */
    public void setFloor_name(String floor_name) {
        this.floor_name = floor_name;
    }

    /**
     *
     * @return department code
     */
    public Long getFOAPAL_code() {
        return FOAPAL_code;
    }

    /**
     * it takes the FOAPAL (Department) code and send it to the database
     *
     * @param FOAPAL_code contains department number
     */
    public void setFOAPAL_code(Long FOAPAL_code) {
        this.FOAPAL_code = FOAPAL_code;
    }

    /**
     *
     * @return room description
     */
    public String getRoom_type_des() {
        return room_type_des;
    }

   /**
    * set the room description to the assigned room
    * @param room_type_des contains the room description 
    */
    public void setRoom_type_des(String room_type_des) {
        this.room_type_des = room_type_des;
    }

    /**
     *
     * @return room area in square feet
     */
    public int getRoom_area_sqft() {
        return room_area_sqft;
    }

    /**
     * it set the room area from the database
     *
     * @param room_area_sqft contains room area by square feet 
     */
    public void setRoom_area_sqft(int room_area_sqft) {
        this.room_area_sqft = room_area_sqft;
    }

}
