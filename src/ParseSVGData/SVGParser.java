/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ParseSVGData;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Nick
 */
public class SVGParser {
    Document svg;
    
    public SVGParser(String filename) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            svg = builder.parse(new FileInputStream(filename));
            
        } catch (FactoryConfigurationError e) {
            System.err.println("unable to get a document builder factory");
        } catch (ParserConfigurationException e) {
            System.err.println("parser was unable to be configured");
        } catch (SAXException e) {
            System.err.println("parsing error");
        } catch (IOException e) {
            System.err.println("io error - bad pathname?");
        }
    }
    
    public RoomData parse() {
        ArrayList<RoomNumberData> rnData = parseRoomNumberData();
        ArrayList<PathData> pData = parsePathData(rnData);
        
        return new RoomData(rnData, pData);
    }
        
    private ArrayList<RoomNumberData> parseRoomNumberData() {
        ArrayList<RoomNumberData> rnData = new ArrayList();
        NodeList roomNumText = svg.getElementsByTagName("text");
        
        int i = 0, j = 0;
        while (roomNumText.item(i) != null) {
            Node textData = roomNumText.item(i);
            String childText = textData.getTextContent();

            if ((!childText.contains(".") && !childText.contains(",")) && (childText.length() == 5  || (childText.length() == 7 && childText.contains("_")))
                    && !childText.equals("Count") && !childText.equals("Total")) {

                //get values of transform attribute [0-5]. The 4th, 5th values are coordinates -- y,x
                String[] transformValues = textData.getAttributes().getNamedItem("transform").getTextContent().split(",");
                for (int l = 4; l <= 5; l++) {
                    String s = transformValues[l];
                    for (int k = 0; k < s.length(); k++) {
                        char c = s.charAt(k);
                        if (!Character.isDigit(c)) {
                            s = s.replace(Character.toString(c), " ");
                        }
                    }
                    s = s.trim();
                    transformValues[l] = s;
                }
                int x = Integer.parseInt(transformValues[5]);
                int y = Integer.parseInt(transformValues[4]);

                rnData.add(new RoomNumberData(childText, x, y));
                j++;
            }
            i++;
        }
        
        return rnData;
    }
    
    private ArrayList<PathData> parsePathData(ArrayList<RoomNumberData> rnData) {
        ArrayList<PathData> pData = new ArrayList();
        NodeList paths = svg.getElementsByTagName("path");

        int i = 0;
        while (paths.item(i) != null) {
            Node path = paths.item(i);
            if (path.getAttributes().getNamedItem("style") != null) {
                String style = path.getAttributes().getNamedItem("style").getTextContent();

                if (!style.substring(5, 9).toLowerCase().equals("none") &&
                        !style.substring(6, 12).equals("000000"))
                    pData.add(new PathData(paths.item(i).getAttributes().getNamedItem("d").getTextContent(), style.substring(6, 12),
                                            paths.item(i).getAttributes().getNamedItem("id").getTextContent()));
            }
            i++;
        }
        
        Stitcher s = new Stitcher(pData, rnData);
        return s.stitch();
        //return pData;
    }
    
}