package ParseSVGData;

/**
 * PointData objects represent a point with two integers, x and y
 * 
 * @author Nick Killion
 */
public class PointData {
    //raw x, y coordinate data
    public int x, y;
    
    /**
     * Constructor for PointData.
     * 
     * @param x X coordinate
     * @param y Y coordinate
     */
    public PointData(int x, int y) {
        this.x = x;
        this.y = y;
    }
   
    /**
     * Default constructor for PointData
     */
    public PointData() {
        this(0, 0);
    }
    
    /**
     * Return string representation of PointData.
     * 
     * @return string representation of PointData.
     */
    public String toString() {
        return ("Y=" + y + " X=" + x);
    }
    
    /**
     * Return true if objects are equal. Does not override Object.equals
     * 
     * @param x X coordinate of point
     * @param y Y coordinate of point
     * @return True only if this object's points are equal to given points
     */
    public boolean equals (int x, int y) {
        return (this.x == x && this.y == y);
    }
    
    /**
     * Return true if objects are equal. Overrides Object.equals
     * 
     * @param o PointData object to be tested
     * @return True only if this object's points are equal to given points
     */
    @Override
    public boolean equals(Object o) {
        PointData pd = (PointData) o;
        return equals(pd.x, pd.y);
    }
    
    /**
     * Determine whether given PointData's x coordinate is very close to this PointData's x coordinate
     * 
     * @param pd PointData object
     * @return true if points are close to being equal
     */
    public boolean closeX(PointData pd) {
        return (pd.x > this.x - 1 && pd.x < this.x + 1);
    }
    
    /**
     * Determine whether given PointData's y coordinate is very close to this PointData's y coordinate
     * 
     * @param pd PointData object
     * @return true if points are close to being equal
     */
    public boolean closeY(PointData pd) {
        return (pd.y > this.y - 1 && pd.y < this.y + 1);
    }
    
    /**
     * Determine whether given PointData's coordinates are very close to this PointData's coordinates
     * 
     * @param pd PointData object
     * @return true if points are close to being equal
     */
    public boolean closeTo(PointData pd) {
        return (closeY(pd) && closeX(pd));
    }
}
