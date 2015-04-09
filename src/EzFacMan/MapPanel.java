/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package EzFacMan;

import ParseSVGData.RoomData;
import ParseSVGData.PointData;
import ParseSVGData.Room;
import ParseSVGData.PathData;
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
    int mapMaxX, mapMaxY, mapMinX, mapMinY;
    PointData mapCenter, panelCenter;
    double scale;
    Dimension dim;
    int x1, x2, y1, y2, textX, textY;
    String selectedRoom = null;
    
    public MapPanel() {
        super();

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                mouseClickEvent(evt.getX(), evt.getY());
            }
        });
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
    
    public void selectRoom(String roomNum) {
        selectedRoom = roomNum;
        repaint();
    }
    
    public void unselectRoom() {
        selectedRoom = null;
        repaint();
    }
    
    public String getSelectedRoom() {
        return selectedRoom;
    }
    
    public String mouseClickEvent(int x, int y) {
        int convX = x;
        int convY = y;
        convX -= (panelCenter.x - mapCenter.x);
        convX *= scale;
        convX = mapMaxX - convX;
       
        convY -= (panelCenter.y - mapCenter.y);
        convY *= scale;
        convY = mapMaxY - convY;
        
        
       
        String roomClicked = "";
        
        for (Room r : rList.roomList) {
            PathData pd = new PathData(r.points);
            if (pd.contains(convX, convY))
                roomClicked = r.roomNum;
        }
        
        if (roomClicked.isEmpty() || roomClicked.equals(getSelectedRoom())) {
            unselectRoom();
        }
        else {
            selectRoom(roomClicked);
        }
        repaint();
        
        return roomClicked;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (isInitialized) {
            if (dim != null && !dim.equals(this.getSize()))
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
                    x1 = (int)((mapMaxX-p1.x)/scale);
                    x1 += (panelCenter.x - mapCenter.x);
                    y1 = (int)((mapMaxY-p1.y)/scale);
                    y1 += (panelCenter.y - mapCenter.y);
                    x2 = (int)((mapMaxX-p2.x)/scale);
                    x2 += (panelCenter.x - mapCenter.x);
                    y2 = (int)((mapMaxY-p2.y)/scale);
                    y2 += (panelCenter.y - mapCenter.y);
                    
                    g.drawLine(x1, y1, x2, y2);
                    if (selectedRoom != null && room.roomNum.equals(selectedRoom)) {
                        g.drawLine(x1+1, y1+1, x2+1, y2+1);
                        g.drawLine(x1-1, y1-1, x2-1, y2-1);
                        g.drawLine(x1-1, y1, x2-1, y2);
                        g.drawLine(x1-1, y1, x2-1, y2);
                        g.drawLine(x1, y1-1, x2, y2-1);
                        g.drawLine(x1, y1-1, x2, y2-1);
                    }
                    j++;
                }
                textX = (int)((mapMaxX-room.roomNumCoords.x)/scale);
                textX += (panelCenter.x - mapCenter.x);
                textY = (int)((mapMaxY-room.roomNumCoords.y)/scale);
                textY += (panelCenter.y - mapCenter.y);

                g.drawString(room.roomNum, textX, textY);
            }
            g.setFont(new Font(null, 0, 15));
        }
    }
    
    private void getMinMaxCoords() {
        for (Room room : rList.roomList) {
            for (PointData point : room.points) {
                if (point.x > mapMaxX)
                    mapMaxX = point.x;
                if (point.y > mapMaxY)
                    mapMaxY = point.y;
                if (point.x < mapMinX)
                    mapMinX = point.x;
                if (point.y < mapMinY)
                    mapMinY = point.y;
            }
        }
    }
    
    //also calculates center point
    private void calculateScale() {
        getMinMaxCoords();
        dim = this.getSize();
        
        int diffX = dim.width - mapMaxX;
        int diffY = dim.height - mapMaxY;
        
        //unnecessarily complex conditional... refactor later?
        if (diffY > 0 && diffX > 0) {
            if (diffY > diffX)
                scale = dim.height/(double)mapMaxY;
            else
                scale = dim.width/(double)mapMaxX;
        }
        else if (diffY < 0 && diffX < 0) {
            if (diffY > diffX)
                scale = mapMaxY/(double)dim.height;
            else
                scale = mapMaxX/(double)dim.width;
        }
        else if (diffY > diffX)
            scale = dim.height/(double)mapMaxY;
        else if (diffY < diffX)
            scale = dim.width/(double)mapMaxX;
        
        scale += scale * .05;
    
        panelCenter = new PointData((int)(dim.width/2), (int)(dim.height/2));
        mapCenter = new PointData((int)((mapMaxX-mapMinX)/(scale * 2)), (int)((mapMaxY-mapMinY)/(scale * 2)));
        
    }
}
