/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package library.formsPopUp;

import library.components.NotificationItem;
import library.model.ModelNotification;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import library.database.DatabaseConnection;


public class NotifyPopUp extends javax.swing.JPanel {

    
    public NotifyPopUp() {
        initComponents();
        setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setViewportBorder(null);
        
    }
    
    public void addNotification(ModelNotification data){
        NotificationItem item = new NotificationItem();
        item.setData(data);
        
        panelItem1.add(item);
        panelItem1.repaint();
        panelItem1.revalidate();
    }
    public ModelNotification populateCountNotification(String userId) {
    ModelNotification count = new ModelNotification();

    try {
        String sql = "SELECT COUNT(*) AS totalCount FROM customer_rented_books_data_v2 WHERE userId = ? AND DATE(dateReturn) <= CURRENT_DATE()";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
        p.setString(1, userId);
        ResultSet rs = p.executeQuery();

        if (rs.next()) {
            count.setNumberOfData(rs.getInt("totalCount"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return count;
}
     public void populateNotification(String userId) {
    try {
        String sql = "SELECT firstName, lastName, bookRented, dateReturn, (SELECT COUNT(*) FROM customer_rented_books_data_v2  WHERE userId = ? AND DATE(dateReturn) <= CURRENT_DATE()) as totalData FROM customer_rented_books_data_v2  WHERE userId = ? AND DATE(dateReturn) <= CURRENT_DATE()";
                     
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
        p.setString(1, userId);
        p.setString(2, userId);
        ResultSet rs = p.executeQuery();
        
        while (rs.next()) {
            ModelNotification itemNotification = new ModelNotification(
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("bookRented"),
                rs.getDate("dateReturn")
            );
            
            
            addNotification(itemNotification);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new library.components.RoundPanel();
        scroll = new javax.swing.JScrollPane();
        panelItem1 = new library.swing.PanelItem();
        jLabel1 = new javax.swing.JLabel();

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setRoundBottomLeft(100);
        roundPanel1.setRoundBottomRight(100);
        roundPanel1.setRoundTopLeft(100);
        roundPanel1.setRoundTopRight(100);

        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setViewportView(panelItem1);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("Notification");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(215, Short.MAX_VALUE))
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addComponent(scroll)
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    public library.swing.PanelItem panelItem1;
    private library.components.RoundPanel roundPanel1;
    private javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables
}
