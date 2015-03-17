package ParseSVGData;

/**
 * Stores all relevant information for a Room Number from SVG map
 * <p>
 * Stores a PointData object representing coordinates of room number, and the room number itself
 * 
 * @author Nick Killion
 */
public class RoomNumberData {
    //raw x, y coordinate data
    public PointData coords;
    public String roomNum;
    
    /**
     * Constructor for RoomNumberData
     * 
     * @param roomNum String Room Number
     * @param x int, x coordinate of Room Number Text
     * @param y int, y coordinate of Room Number Text
     */
    public RoomNumberData(String roomNum, int x, int y) {
        coords = new PointData(x, y);
        this.roomNum = roomNum;
    }
    
    /**
     * Returns string representation of RoomNumberData
     * 
     * @return string representation of RoomNumberData
     */
    public String toString() {
        return ("Room " + roomNum + ": " + coords.toString());
    }
}
