package ParseSVGData;
import java.util.ArrayList;

/**
 * Represents all rooms on a floor.
 * Consists of an ArrayList of Room objects.
 *
 * @author Nick Killion
 *
 */
public class RoomData {
    public ArrayList<Room> roomList;
    
    /**
     * Constructor for RoomData.
     * 
     * @param rnData ArrayList of RoomNumberData Objects
     * @param pdata ArrayList of PathData objects
     */
    public RoomData(ArrayList<RoomNumberData> rnData, ArrayList <PathData> pdata) {
        roomList = new ArrayList();
        ArrayList<RoomNumberData> rnData_copy = (ArrayList<RoomNumberData>) rnData.clone();
        
        for (PathData pd : pdata) {
            for (RoomNumberData r : rnData) {
                if (pd.contains(r.coords.x, r.coords.y)) {
                    roomList.add(new Room(pd, r));
                    rnData_copy.remove(r);
                }
            }
        }
    }
}
