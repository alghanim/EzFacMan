/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ParseSVGData;

import java.util.ArrayList;

/**
 *
 * @author Nick
 */
public class Room {
    ArrayList<PointData> points;
    String color;
    String roomNum;
    PointData roomNumCoords;
    
    public Room(PathData pd, RoomNumberData rnData) {
        PathData path = pd;
        color = pd.color;
        points = pd.points;
        roomNum = rnData.roomNum;
        roomNumCoords = rnData.coords;
    }
    
    public String toString() {
        String ret = roomNum;
        
        for (PointData p : points) {
            ret += p.toString();
        }
        return ret;
    }
}
