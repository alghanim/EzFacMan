/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EzFacMan;

import databaseTables.building;
import databaseTables.buildingManager;
import databaseTables.campus;
import databaseTables.campusManager;
import databaseTables.floors;
import databaseTables.floorsManager;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ali
 */
public class SearchPanel extends javax.swing.JFrame {

    public ArrayList<String> allcampuses = new ArrayList<String>();
    private ArrayList<String> allbuildings = new ArrayList<String>();
    private ArrayList<String> allfloors = new ArrayList<String>();

    public String dBuilding;
    public static String dCampus;
    public String dFloor;
    
    public SearchPanel() throws SQLException, ClassNotFoundException {

        initComponents();

        floors f = floorsManager.displayAllfloors();
        campus c = campusManager.displayAllCampuses();
        building b = buildingManager.displayAllBuildings();

        floorDrop.removeAllItems();
        campusDrop.removeAllItems();
        buildingDrop.removeAllItems();
        allfloors.addAll(f.getAllFloors());
        allcampuses.addAll(c.getAllcampuses());
        allbuildings.addAll(b.getAllbuildings());
        //  System.out.println(f.getAllFloors().toString());
        for (String s : allfloors) {
            floorDrop.addItem(s);
        }
        for(String s: allcampuses){
            campusDrop.addItem(s);
        }
        for(String s: allbuildings){
            buildingDrop.addItem(s);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buildingDrop = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        campusDrop = new javax.swing.JComboBox();
        floorDrop = new javax.swing.JComboBox();
        roomInput = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(214, 214));

        buildingDrop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildingDropActionPerformed(evt);
            }
        });

        jLabel1.setText("Choose a building");

        jLabel2.setText("Choose a floor");

        jLabel3.setText("Choose a campus");

        jLabel4.setText("Enter Room#");

        campusDrop.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        campusDrop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campusDropActionPerformed(evt);
            }
        });

        floorDrop.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        floorDrop.setMaximumSize(new java.awt.Dimension(32765067, 65632767));
        floorDrop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                floorDropActionPerformed(evt);
            }
        });

        jButton1.setText("Search");
        jButton1.setActionCommand("searchButton");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        jButton2.setLabel("Exit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(63, 63, 63))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addGap(39, 39, 39)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(buildingDrop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(campusDrop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(floorDrop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(roomInput, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(115, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(campusDrop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(buildingDrop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(floorDrop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(roomInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_searchButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_exitButtonActionPerformed

    private void campusDropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusDropActionPerformed
        // TODO add your handling code here:
              dCampus = (String) campusDrop.getSelectedItem();
      //  String sqlString = "'" + dCampus + "'";

        if (dCampus != null) {

            building bb = null;
            try {
                buildingDrop.removeAllItems();

                bb = buildingManager.display(dCampus);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EZFacUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            allbuildings.removeAll(allbuildings);
            allbuildings.addAll(bb.getAllbuildings());

            allbuildings.stream().forEach((s) -> {
                buildingDrop.addItem(s);

            });
            buildingDrop.setEnabled(true);
            floorDrop.setEnabled(false);

        }

    }//GEN-LAST:event_campusDropActionPerformed

    private void buildingDropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buildingDropActionPerformed
        // TODO add your handling code here:
            dBuilding = (String) buildingDrop.getSelectedItem();

        if (dBuilding != null) {
            floors ff = null;
            try {
                floorDrop.removeAllItems();

                ff = floorsManager.display(dBuilding);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EZFacUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            allfloors.removeAll(allfloors);
            allfloors.addAll(ff.getAllFloors());

            allfloors.stream().forEach((s) -> {
                floorDrop.addItem(s);

            });

        }
        System.out.println(allfloors.toString());
        floorDrop.setEnabled(true);
    }//GEN-LAST:event_buildingDropActionPerformed

    private void floorDropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_floorDropActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_floorDropActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SearchPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new SearchPanel().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(SearchPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SearchPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox buildingDrop;
    private javax.swing.JComboBox campusDrop;
    private javax.swing.JComboBox floorDrop;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField roomInput;
    // End of variables declaration//GEN-END:variables
}
