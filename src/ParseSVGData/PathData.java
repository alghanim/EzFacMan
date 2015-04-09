package ParseSVGData;

import java.util.ArrayList;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.PathIterator;

/**
 * Class to store data for SVG paths.
 * <p>
 * Class also parses raw SVG path data
 * 
 * @author Nick Killion
 */
public class PathData {
    
    //raw svg path data. Specifications outlined in parsePathData method
    public String pathCoords;
    
    //raw hex color string
    public String color;
    
    public String id;
    public ArrayList<PointData> points = new ArrayList();
    
    /**
     * Constructor for PathData class.
     * <p>
     * Input is based on raw data read from SVG
     * 
     * @param pathCoords String representation of coordinates (SVG specification)
     * @param color String hex value (eg "123DEF")
     * @param id SVG path id
     */
    public PathData(String pathCoords, String color, String id) {
        this.pathCoords = pathCoords;
        this.color = color;
        this.id = id;
        
        if (pathCoords != null && color != null) {
            if (!pathCoords.equals("") && !color.equals("")) {
                parsePathData();
                makeClockwiseOrder();
            }
        }
    }
    /**
     * Constructor for PathData class.
     * <p>
     * Input is based on ArrayList of PointData
     * 
     * @param p ArrayList of PointData objects
     * @param color String hex value (eg "123DEF")
     * @param order True if points need to be arranged into clockwise order
     */
    public PathData(ArrayList<PointData> p, String color, boolean order) {
        this.points = p;
        this.color = color;
        this.id = new String();        
        
        if(order) {
            if (points != null && color != null) {
                if (!points.isEmpty() && !color.equals("")) {
                    makeClockwiseOrder();
                }
            }
        }
    }
    
    /**
     * Default constructor for PathData
     */
    public PathData() {
        pathCoords = new String();
        color = new String();
        id = new String();
    }
    
    /**
     * Constructor to be used by the MapPanel class. Constructs PathData object for the purpose of using the contains method.
     * @param pd 
     */
    public PathData(ArrayList<PointData> pd) {
        points = pd;
        color = null;
        id = null;
    }
    
    /**
     * Determine whether two paths are equal
     * 
     * @param p The path to be tested for equality
     * @return True if paths are equal, false if not
     */
    @Override
    public boolean equals(Object p) {
        PathData pd = (PathData) p;
        return (pd.points.equals(this.points) && pd.color.equals(this.color));
    }
    
    /**
     * Returns a string representation of the path data
     * 
     * @return String representation of object
     */
    public String toString() {
        String ret = "*******Printing Path Data***********\n";
        ret += "* Id: " + id;
        ret += "\n* Color: " + color + "\n* Coords:\n";
        for (PointData pd:points) {
            ret += "* " + (pd.toString() + "\n");
        }
        ret += "*****************************\n";
        return ret;
    }
    
    /**
     * Parses raw SVG path data.
     * <p>
     * Absolute coordinates, placed in clockwise order
     */
    private void parsePathData() {
        int x,y;
        String[] instructions = pathCoords.split(" ");        
        String[] relCoords = instructions[1].split(",");
        
        y = Integer.parseInt(relCoords[0]);
        x = Integer.parseInt(relCoords[1]);
        points.add(new PointData(x, y));
        
        for (int i = 2; i < instructions.length; i++) {
            switch (instructions[i]) {
                case "z":
                case "Z":
                case "m":
                case "M":
                    break;
                case "l":
                case "L":
                    System.out.println("ERROR");
                    break;
                default:
                    relCoords = instructions[i].split(",");
                    y += Integer.parseInt(relCoords[0]);
                    x += Integer.parseInt(relCoords[1]);
                    
                    boolean duplicate = false;
                    for(PointData pd : points) {
                        if (pd.equals(x,y))
                            duplicate = true;
                    }
                
                    if (!duplicate)
                        points.add(new PointData(x, y));
            }
        }
    }
    
    /**
     * Puts points in clockwise order.
     * <p>
     * This is necessary for later computation
     */
    private void makeClockwiseOrder() {
        PointData center = getCenter();
        for (int i = 1; i < points.size(); i++) {
            PointData tmp = points.get(i);
            int j;
            for (j = i - 1; j >= 0 && lessThan(tmp, points.get(j), center); j--) {
                points.set(j + 1, points.get(j));
            }
            points.set(j+1, tmp);
        }
    }
    
    /**
     * Returns average of all points. Used as center to determine clockwise order
     */
    private PointData getCenter() {
        int x = 0;
        int y = 0;
        
        for (PointData pd : points) {
            x += pd.x;
            y += pd.y;
        }
        
        x /= (double) points.size();
        y /= (double) points.size();
        
        return new PointData(x,y);
    }
    
    /**
     * Determines whether PointData a is clockwise-less-than PointData b.
     * 
     * @param a PointData object
     * @param b PointData object
     * @param center The average of all points. Used as center of clockwise ordering
     * @return True only if PointData a is clockwise-less-than PointData b
     */
    private boolean lessThan(PointData a, PointData b, PointData center) {
        //cross product
        double det = (a.x - center.x) * (b.y - center.y) - (b.x - center.x) * (a.y - center.y);
        if (det > 0)
            return true;
        return false;
    }
    
    /**
     * Return true if this path surrounds point given by x, y
     * @param x int, x coordinate
     * @param y int, y coordinate
     * @return True only if this path surrounds point given by x, y
     */
    public boolean contains(int x, int y) {
        Path2D.Double path = new Path2D.Double();
        path.moveTo(points.get(0).x, points.get(0).y);
        
        for (int i = 1; i < points.size(); i++) {
            PointData p = points.get(i);
            path.lineTo(p.x, p.y);
        }
        
        return path.contains((double)x, (double)y);
    }
}
