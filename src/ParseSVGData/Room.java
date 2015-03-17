package ParseSVGData;

import java.util.ArrayList;

/**
 * Representation of an EZ-Fac Room.
 * <p>
 * Contains ArrayList of PointData, string color, string room number, and PointData of room number's coordinates
 * 
 * @author Nick Killion
 */
public class Room {
    ArrayList<PointData> points;
    String color;
    String roomNum;
    PointData roomNumCoords;
    
    /**
     * Constructor for Room
     * 
     * @param pd PathData object for room
     * @param rnData roomNumberData object of room
     */
    public Room(PathData pd, RoomNumberData rnData) {
        PathData path = pd;
        color = pd.color;
        points = pd.points;
        roomNum = rnData.roomNum;
        roomNumCoords = rnData.coords;
    }
    
    /**
     * Returns string representation of Room
     * 
     * @return string representation of Room
     */
    public String toString() {
        String ret = roomNum;
        
        for (PointData p : points) {
            ret += p.toString();
        }
        return ret;
    }
}
