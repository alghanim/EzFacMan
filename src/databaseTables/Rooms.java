package databaseTables;

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
    
    public String getRoom_color() {
        return room_color;
    }
    public void setRoom_color(String room_color) {
        this.room_color = room_color;
    }
    
    public String getComments() {
        return comment_box;
    }
    
    public void setComments(String comment_box) {
        this.comment_box = comment_box;
    }
    
    public String getBuilding_name() {
        return building_name;
    }

    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }

    public String getFOAPAL_name() {
        return FOAPAL_name;
    }

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
     * @param building_code
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
     * @param floor_name
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
     * @param FOAPAL_code
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
     *
     * @param room_type_des
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
     * it takes the room area and send it to the database
     *
     * @param room_area_sqft
     */
    public void setRoom_area_sqft(int room_area_sqft) {
        this.room_area_sqft = room_area_sqft;
    }

}
