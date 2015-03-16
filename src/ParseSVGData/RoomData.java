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
public class RoomData {
    public ArrayList<Room> roomList;
    
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
