/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package EzFacMan;

import ParseSVGData.RoomData;
import ParseSVGData.PointData;
import ParseSVGData.Room;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Font;
import java.io.Serializable;

/**
 *
 * @author Nick
 */
public class MapPanel extends JPanel implements Serializable {
    
    boolean isInitialized = false;
    private RoomData rList;     //keep private! Use setRoomList()
    int maxX, maxY;
    PointData mapCenter, panelCenter;
    double scale;
    Dimension size;
    int x1, x2, y1, y2, textX, textY;
    
    public MapPanel() {
        super();
    }
    
    /**
     * Initializes a list of Rooms for this MapPanel.
     * 
     * @param list the ArrayList of Room objects to be drawn to the MapPanel
     */
    public void setRoomList(RoomData list) {
        if (list != null) {
            rList = list;
            calculateScale();
            isInitialized = true;
        }
    }
    
    public RoomData getRoomList() {
        return rList;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (isInitialized) {
            if (size != null && !size.equals(getSize()))
                calculateScale();
                
            g.setFont(new Font(null, 0, 9));

            int j,k;
            //get and draw points
            for (Room room : rList.roomList) {
                j = 0;
                
                while (j < room.points.size()) {
                    PointData p1 = room.points.get(j);
                    if (j < room.points.size()-1)
                        k = j+1;
                    else
                        k = 0;
                    PointData p2 = room.points.get(k);

                    //convert to Swing coord system. Also place points at center of panel
                    x1 = (int)((maxX-p1.x)/scale);
                    x1 += (panelCenter.x - mapCenter.x);
                    y1 = (int)((maxY-p1.y)/scale);
                    y1 += (panelCenter.y - mapCenter.y);
                    x2 = (int)((maxX-p2.x)/scale);
                    x2 += (panelCenter.x - mapCenter.x);
                    y2 = (int)((maxY-p2.y)/scale);
                    y2 += (panelCenter.y - mapCenter.y);
                    
                    g.drawLine(x1, y1, x2, y2);

                    j++;
                }
                textX = (int)((maxX-room.roomNumCoords.x)/scale);
                textX += (panelCenter.x - mapCenter.x);
                textY = (int)((maxY-room.roomNumCoords.y)/scale);
                textY += (panelCenter.y - mapCenter.y);

                g.drawString(room.roomNum, textX, textY);
            }
            g.setFont(new Font(null, 0, 15));
            g.drawString("P1", size.width-5, panelCenter.y);
            g.drawString("P2", 5, panelCenter.y);
            g.drawString("M", mapCenter.x, mapCenter.y);
        }
    }
    
    private void getMaxCoords() {
        for (Room room : rList.roomList) {
            for (PointData point : room.points) {
                if (point.x > maxX)
                    maxX = point.x;
                if (point.y > maxY)
                    maxY = point.y;
            }
        }
    }
    
    //also calculates center point
    private void calculateScale() {
        getMaxCoords();
        size = getSize();
        
        int diffX = size.width - maxX;
        int diffY = size.height - maxY;
        
        //unnecessarily complex conditional... refactor later?
        if (diffY > 0 && diffX > 0) {
            if (diffY > diffX)
                scale = size.height/(double)maxY;
            else
                scale = size.width/(double)maxX;
        }
        else if (diffY < 0 && diffX < 0) {
            if (diffY > diffX)
                scale = maxY/(double)size.height;
            else
                scale = maxX/(double)size.width;
        }
        else if (diffY > diffX)
            scale = size.height/(double)maxY;
        else if (diffY < diffX)
            scale = size.width/(double)maxX;
    
        panelCenter = new PointData((int)(size.width/2), (int)(size.height/2));
        mapCenter = new PointData((int)(maxX/(scale * 2)), (int)(maxY/(scale * 2)));
        
    }
}
