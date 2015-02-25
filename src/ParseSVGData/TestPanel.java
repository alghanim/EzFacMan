package ParseSVGData;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import java.awt.Font;

class TestPanel extends JPanel {
    ArrayList<Room> rList;
    
    public TestPanel(ArrayList<Room> rList) {
        this.rList = rList;
    }
    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.blue);
        Dimension size = getSize();
        Insets insets = getInsets();

        int w = size.width - insets.left - insets.right;
        int h = size.height - insets.top - insets.bottom;

        //need max values to change coords to fit this coord system
        int maxX = 0;
        int maxY = 0;
        for (Room room : rList) {
            for (PointData point : room.points) {
                if (point.x > maxX)
                    maxX = point.x;
                if (point.y > maxY)
                    maxY = point.y;
            }
        }

        int x1, y1, x2, y2;
        int scale = 9;
        g.setFont(new Font(null, 0, 9));
        
        int j,k,d;
        //get and draw points
        for (Room room : rList) {
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
}