/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ParseSVGData;
import java.util.ArrayList;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.PathIterator;

/**
 *
 * @author tud47465
 */
public class PathData {
    
    //raw svg path data. Specifications outlined in parsePathData method
    public String pathCoords;
    
    //raw hex color string
    public String color;
    
    public String id;
    
    //list of absolute path coordinates
    ArrayList<PointData> points = new ArrayList();
    
       
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
    
    public PathData() {
        pathCoords = new String();
        color = new String();
        id = new String();
    }
    
    @Override
    public boolean equals(Object p) {
        PathData pd = (PathData) p;
        return (pd.points.equals(this.points) && pd.color.equals(this.color));
    }
    
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
    
    //gets all points in cw order. needed for later computation
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
    
    //returns average of all poitns. used as center to determine cw order
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
    
    //determines if a is clockwise-less-than b, using center
    private boolean lessThan(PointData a, PointData b, PointData center) {
        //cross product
        double det = (a.x - center.x) * (b.y - center.y) - (b.x - center.x) * (a.y - center.y);
        if (det > 0)
            return true;
        return false;
    }
    
    //return true if path surrounds given coordinates
    //using awt methods, to simplify things
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
