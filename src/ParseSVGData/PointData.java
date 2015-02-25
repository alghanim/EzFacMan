/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ParseSVGData;

/**
 *
 * @author tud47465
 */
public class PointData {
    //raw x, y coordinate data
    public int x, y;
    
    //X,Y. not Y,X
    public PointData(int x, int y) {
        this.x = x;
        this.y = y;
    }
   
    public PointData() {
        this(0, 0);
    }
    
    public String toString() {
        return ("Y=" + y + " X=" + x);
    }
    
    public boolean equals (int x, int y) {
        return (this.x == x && this.y == y);
    }
    
    @Override
    public boolean equals(Object o) {
        PointData pd = (PointData) o;
        return equals(pd.x, pd.y);
    }
    
    public boolean closeX(PointData pd) {
        return (pd.x > this.x - 1 && pd.x < this.x + 1);
    }
    
    public boolean closeY(PointData pd) {
        return (pd.y > this.y - 1 && pd.y < this.y + 1);
    }
    
    public boolean closeTo(PointData pd) {
        return (closeY(pd) && closeX(pd));
    }
}
