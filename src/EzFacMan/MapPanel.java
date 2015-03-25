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
import java.awt.Insets;
import java.io.Serializable;

/**
 *
 * @author Nick
 */
public class MapPanel extends JPanel implements Serializable {
    
    RoomData rList;
    
    public MapPanel() {
        super();
    }
    
    public void setRoomList(RoomData list) {
        rList = list;
    }
    
    public RoomData getRoomList() {
        return rList;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (rList != null) {
            Dimension size = getSize();
            Insets insets = getInsets();
            int w = size.width - insets.left - insets.right;
            int h = size.height - insets.top - insets.bottom;

            //need max values to change coords to fit this coord system
            int maxX = 0;
            int maxY = 0;
            for (Room room : rList.roomList) {
                for (PointData point : room.points) {
                    if (point.x > maxX)
                        maxX = point.x;
                    if (point.y > maxY)
                        maxY = point.y;
                }
            }

            int x1, y1, x2, y2;
            int scale = 12;
            g.setFont(new Font(null, 0, 9));

            int j,k,d;
            //get and draw points
            for (Room room : rList.roomList) {
                j = 0;
                d = size.width;
                k = d;
                while (j < room.points.size()) {
                    PointData p1 = room.points.get(j);
                    if (j < room.points.size()-1)
                        k = j+1;
                    else
                        k = 0;
                    PointData p2 = room.points.get(k);

                    //different coord system...
                    x1 = (maxX-p1.x)/scale;
                    y1 = (maxY-p1.y)/scale;
                    x2 = (maxX-p2.x)/scale;
                    y2 = (maxY-p2.y)/scale;

                    g.drawLine(x1, y1, x2, y2);

                    j++;
                }

                g.drawString(room.roomNum, (maxX-room.roomNumCoords.x)/scale, (maxY-room.roomNumCoords.y)/scale);
            }
        }
    }
}
