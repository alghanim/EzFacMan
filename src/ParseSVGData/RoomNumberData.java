/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ParseSVGData;

/**
 *
 * @author tud47465
 */
public class RoomNumberData {
    //raw x, y coordinate data
    public PointData coords;
    public String roomNum;
    
    //X,Y. not Y,X
    public RoomNumberData(String roomNum, int x, int y) {
        coords = new PointData(x, y);
        this.roomNum = roomNum;
    }
    
    public String toString() {
        return ("Room " + roomNum + ": " + coords.toString());
    }
}
