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
import databaseTables.Rooms;
import databaseTables.RoomsManager;
import databaseTables.department;
import databaseTables.departmentManager;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Polygon;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
    String roomColor = "49F41E";
    
    public MapPanel() {
        super();

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                try {
                    if (isInitialized)
                        mouseClickEvent(evt.getX(), evt.getY());
                } catch (SQLException ex) {
                    Logger.getLogger(MapPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MapPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
    }

    /**
     * Initializes a list of Rooms for this MapPanel.
     *
     * @param list the ArrayList of Room objects to be drawn to the MapPanel
     */
    public void setRoomList(RoomData list) {
        isInitialized = false;
        if (list != null) {
            rList = list;
            calculateScale();
            getColors();
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

    public String mouseClickEvent(int x, int y) throws SQLException, ClassNotFoundException {
        //   EZFacUI ez = new EZFacUI();
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
            if (pd.contains(convX, convY)) {
                roomClicked = r.roomNum;
            }
        }

        if (roomClicked.isEmpty() || roomClicked.equals(getSelectedRoom()))
            unselectRoom();
        else {
            selectRoom(roomClicked);

            showRoomInfo(roomClicked);
        }

        repaint();

        return roomClicked;
    }
/**
 * Set all room info for the clicked room to the pop out window and display it 
 * @param roomClicked
 * @throws ClassNotFoundException
 * @throws SQLException 
 */
    public void showRoomInfo(String roomClicked) throws ClassNotFoundException, SQLException {
        EZFacUI ez = new EZFacUI();
        Rooms RoomsObject = RoomsManager.displayCertainRooms(roomClicked, EZFacUI.dBuilding);
        if (RoomsObject != null) {
            ez.Add.setEnabled(false);
            ez.campusCode.setText(ez.dCampus);
            ez.roomNum.setText(RoomsObject.getRoom_num());
            ez.departmentCode.setText(RoomsObject.getFOAPAL_code().toString());
            ez.roomType.setText(RoomsObject.getRoom_type_des());
            ez.floorName.setText(RoomsObject.getFloor_name());
            ez.departmentName.setText(RoomsObject.getFOAPAL_name());
            ez.buildingName.setText(RoomsObject.getBuilding_name());
            ez.roomArea.setText(String.valueOf(RoomsObject.getRoom_area_sqft()));
            ez.commentBox.setText(RoomsObject.getComments());
            ez.colorPanel.setBackground(Color.decode("#" + RoomsObject.getRoom_color()));
            
            ez.roomPopUp.setTitle("Room Information");
            ez.roomPopUp.setVisible(true);
        } else {
            int reply = JOptionPane.showConfirmDialog(null, "There is no room data exists. Do you still want to continue?", "No room data exists", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                ez.updateChange.setEnabled(false);
                ez.campusCode.setText(ez.dCampus);
                ez.buildingName.setText(ez.dBuilding);
                ez.floorName.setText(ez.dFloor);
                ez.roomNum.setText(roomClicked);
                ez.departmentCode.setText("");
                ez.roomType.setText("");
                ez.departmentName.setText("");
                ez.roomArea.setText("");
                ez.commentBox.setText("");
                ez.colorPanel.setBackground(Color.decode("#" + roomColor)); //Default color for department
                ez.roomPopUp.setTitle("Room Information");
                ez.roomPopUp.setVisible(true);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        
        int pdx, pdy;
        
        if (isInitialized) {
            calculateScale();

            g.setFont(new Font(null, 0, 9));
            Polygon p;

            int j, k;
            //get and draw points
            for (Room room : rList.roomList) {
                j = 0;
                p = new Polygon();
                for (PointData pd : room.points) {
                    pdx = (int) ((mapMaxX - pd.x) / scale);
                    pdx += (panelCenter.x - mapCenter.x);
                    pdy = (int) ((mapMaxY - pd.y) / scale);
                    pdy += (panelCenter.y - mapCenter.y);
                    p.addPoint(pdx, pdy);
                }
                
                g.setColor(new Color(Integer.decode("#" + room.color)));
                g.fillPolygon(p);
                g.setColor(Color.BLACK);
                
                while (j < room.points.size()) {
                    PointData p1 = room.points.get(j);
                    if (j < room.points.size() - 1) {
                        k = j + 1;
                    } else {
                        k = 0;
                    }
                    PointData p2 = room.points.get(k);

                    //convert to Swing coord system. Also place points at center of panel
                    x1 = (int) ((mapMaxX - p1.x) / scale);
                    x1 += (panelCenter.x - mapCenter.x);
                    y1 = (int) ((mapMaxY - p1.y) / scale);
                    y1 += (panelCenter.y - mapCenter.y);
                    x2 = (int) ((mapMaxX - p2.x) / scale);
                    x2 += (panelCenter.x - mapCenter.x);
                    y2 = (int) ((mapMaxY - p2.y) / scale);
                    y2 += (panelCenter.y - mapCenter.y);

                    g.drawLine(x1, y1, x2, y2);
                    if (selectedRoom != null && room.roomNum.equals(selectedRoom)) {
                        g.drawLine(x1 + 1, y1 + 1, x2 + 1, y2 + 1);
                        g.drawLine(x1 - 1, y1 - 1, x2 - 1, y2 - 1);
                        g.drawLine(x1 - 1, y1, x2 - 1, y2);
                        g.drawLine(x1 - 1, y1, x2 - 1, y2);
                        g.drawLine(x1, y1 - 1, x2, y2 - 1);
                        g.drawLine(x1, y1 - 1, x2, y2 - 1);
                    }
                    j++;
                }
            }
            g.setFont(new Font(null, 0, 10));
            for (Room room : rList.roomList)
            {
                textX = (int) ((mapMaxX - room.roomNumCoords.x) / scale);
                textX += (panelCenter.x - mapCenter.x);
                textY = (int) ((mapMaxY - room.roomNumCoords.y) / scale);
                textY += (panelCenter.y - mapCenter.y);

                g.drawString(room.roomNum, textX, textY + 4);
            }
        }
    }

    private void getMinMaxCoords() {
        for (Room room : rList.roomList) {
            for (PointData point : room.points) {
                if (point.x > mapMaxX) {
                    mapMaxX = point.x;
                }
                if (point.y > mapMaxY) {
                    mapMaxY = point.y;
                }
                if (point.x < mapMinX) {
                    mapMinX = point.x;
                }
                if (point.y < mapMinY) {
                    mapMinY = point.y;
                }
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
            if (diffY > diffX) {
                scale = dim.height / (double) mapMaxY;
            } else {
                scale = dim.width / (double) mapMaxX;
            }
        } else if (diffY < 0 && diffX < 0) {
            if (diffY > diffX) {
                scale = mapMaxY / (double) dim.height;
            } else {
                scale = mapMaxX / (double) dim.width;
            }
        } else if (diffY > diffX) {
            scale = dim.height / (double) mapMaxY;
        } else if (diffY < diffX) {
            scale = dim.width / (double) mapMaxX;
        }

        scale *= 1.05;

        panelCenter = new PointData((int) (dim.width / 2), (int) (dim.height / 2));
        mapCenter = new PointData((int) ((mapMaxX - mapMinX) / (scale * 2)), (int) ((mapMaxY - mapMinY) / (scale * 2)));

    }
    
    private void getColors() {
    
        for (Room room : rList.roomList) {
            try {
                department departmentObject = departmentManager.getColor(room.roomNum , EZFacUI.dFloor , EZFacUI.dBuilding);
                //System.out.println(room.roomNum + EZFacUI.dFloor + EZFacUI.dBuilding);
                Rooms roomObject = RoomsManager.getRoomColor(room.roomNum, EZFacUI.dBuilding);
                if (roomObject != null) {
                    room.color = roomObject.getRoom_color();
                } else if (departmentObject != null) {
                    room.color = departmentObject.getFOAPAL_color();
                    roomColor = room.color;
                } else
                    room.color = "FFFFFF";
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MapPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MapPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
            isInitialized = true;
    
    }
}
