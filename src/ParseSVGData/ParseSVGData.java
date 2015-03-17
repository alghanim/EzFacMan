package ParseSVGData;
 
import java.util.ArrayList;

/**
 * Driver class to test parsing functionality
 * 
 * @author Nick Killion
 */
public class ParseSVGData {

    /**
     * Creates SVGParser Object, to test parsing methods
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        String filename = "C:\\Documents and Settings\\Nick\\Desktop\\capstone\\svgs\\603SFCB3.svg";
//        String filename = "C:\\Documents and Settings\\Nick\\Desktop\\capstone\\svgs\\RHA09.svg";
        String filename = "C:\\Documents and Settings\\Nick\\Desktop\\capstone\\svgs\\602PB.svg";
        
        SVGParser parser = new SVGParser(filename);        
        RoomData rooms = parser.parse();
        
        test(rooms.roomList);
    }
    
    /**
     * Creates Test object, to test parsing
     * 
     * @param rList ArrayList of Room objects
     */
    public static void test(ArrayList<Room> rList) {
        Test test = new Test(rList);
        test.start();
    }
}
