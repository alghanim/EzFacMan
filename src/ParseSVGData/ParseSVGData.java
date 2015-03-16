/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParseSVGData;
 

import java.io.IOException;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.FileInputStream;
import java.util.ArrayList;

/**
 *
 * @author Nick Killion
 */
public class ParseSVGData {

    /**
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
    
    
    public static void test(ArrayList<Room> rList) {
        Test test = new Test(rList);
        test.start();
    }
}
