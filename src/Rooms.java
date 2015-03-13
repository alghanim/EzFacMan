/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ali
 */
public class Rooms {
    
    private String room_num;
    private int building_code;
    private String floor_name;
    private Long FOAPAL_code;
    private String room_type_des;
    private int room_area_sqft;

    public String getRoom_num() {
        return room_num;
    }

    public void setRoom_num(String room_num) {
        this.room_num = room_num;
    }

    public int getBuilding_code() {
        return building_code;
    }

    public void setBuilding_code(int building_code) {
        this.building_code = building_code;
    }

    public String getFloor_name() {
        return floor_name;
    }

    public void setFloor_name(String floor_name) {
        this.floor_name = floor_name;
    }

    public Long getFOAPAL_code() {
        return FOAPAL_code;
    }

    public void setFOAPAL_code(Long FOAPAL_code) {
        this.FOAPAL_code = FOAPAL_code;
    }

    public String getRoom_type_des() {
        return room_type_des;
    }

    public void setRoom_type_des(String room_type_des) {
        this.room_type_des = room_type_des;
    }

    public int getRoom_area_sqft() {
        return room_area_sqft;
    }

    public void setRoom_area_sqft(int room_area_sqft) {
        this.room_area_sqft = room_area_sqft;
    }
    
    
}
