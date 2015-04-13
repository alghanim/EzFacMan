package ParseSVGData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Class to parse SVG floor map
 *
 * @author Nick Killion
 */
public class SVGParser {

    Document svg;

    /**
     * Constructor for SVGParser. Takes a PDF, converts to SVG, parses it
     *
     * @param inFile String representing path to filename
     */
    public SVGParser(String inFile) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            String file = new String();

            if (inFile.toLowerCase().endsWith("pdf")) {
                file = pdfToSvg(inFile);
            }

            svg = builder.parse(new FileInputStream(file));

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

    /**
     * Parse the SVG file.
     *
     * @return RoomData object containing parsed data
     */
    public RoomData parse() {
        ArrayList<RoomNumberData> rnData = parseRoomNumberData();
        ArrayList<PathData> pData = parsePathData(rnData);

        return new RoomData(rnData, pData);
    }

    private String pdfToSvg(String filename) {
        String svgFileName = filename.substring(0, filename.length() - 3).concat("svg");
        String inkscapeInstallLoc;
        String sysCall = "";
        if (!new File(svgFileName).exists()) {

            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                inkscapeInstallLoc = new String("C:\\Program Files\\Inkscape");

                sysCall = new String("cd " + inkscapeInstallLoc
                        + " && inkscape -l \"" + svgFileName + "\" "
                        + "\"" + filename + "\"");

                System.out.println(sysCall);

            } else if (os.contains("mac")) {

                inkscapeInstallLoc = new String("/Applications/Inkscape.app/Contents/Resources/bin/inkscape");

                sysCall = new String(inkscapeInstallLoc
                        + " -l " + svgFileName
                        + " " + filename);

            }
            System.out.println(sysCall);
            try {
                Process p = Runtime.getRuntime().exec(sysCall);
                p.waitFor();
                p.destroy();
            } catch (IOException ex) {
                Logger.getLogger(SVGParser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {

            }
        }

        return svgFileName;
    }

    /**
     * Parses the SVG file to obtain Room Numbers (text and coordinates)
     *
     * @return ArrayList of RoomNumberData objects
     */
    private ArrayList<RoomNumberData> parseRoomNumberData() {
        ArrayList<RoomNumberData> rnData = new ArrayList();
        NodeList roomNumText = svg.getElementsByTagName("text");

        int i = 0, j = 0;
        boolean hasDigit;
        while (roomNumText.item(i) != null) {
            hasDigit = false;
            Node textData = roomNumText.item(i);
            String childText = textData.getTextContent();

            for (int l = 0; l < childText.length(); l++) {
                if (Character.isDigit(childText.charAt(l))) {
                    hasDigit = true;
                }
            }

            if ((!childText.contains(".") && !childText.contains(",")) && hasDigit
                    && (childText.length() == 5 || (childText.length() == 7 && childText.contains("_")))) {

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

    /**
     * Parse the SVG file to obtain path data
     *
     * @param rnData ArrayList of RoomNumberData obtained from
     * parseRoomNumberData method
     * @return ArrayList of PathData objects representing all paths that might
     * be rooms
     */
    private ArrayList<PathData> parsePathData(ArrayList<RoomNumberData> rnData) {
        ArrayList<PathData> pData = new ArrayList();
        NodeList paths = svg.getElementsByTagName("path");

        int i = 0;
        while (paths.item(i) != null) {
            Node path = paths.item(i);
            if (path.getAttributes().getNamedItem("style") != null) {
                String style = path.getAttributes().getNamedItem("style").getTextContent();

                if (!style.substring(5, 9).toLowerCase().equals("none")
                        && !style.substring(6, 12).equals("000000")) {
                    pData.add(new PathData(paths.item(i).getAttributes().getNamedItem("d").getTextContent(), style.substring(6, 12),
                            paths.item(i).getAttributes().getNamedItem("id").getTextContent()));
                }
            }
            i++;
        }

        Stitcher s = new Stitcher(pData, rnData);
        return s.stitch();
        //return pData;
    }

}
