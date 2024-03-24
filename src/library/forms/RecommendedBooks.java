/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package library.forms;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import library.database.DatabaseConnection;
public class RecommendedBooks extends javax.swing.JPanel {
    private PreparedStatement p;
    private ResultSet rs;
   private DefaultTableCellRenderer centerRenderer;
    public RecommendedBooks() {
        initComponents();
        setOpaque(false);
        centerRenderer = new DefaultTableCellRenderer();
        tableTextCenter();
        tableTextCenterv2();
    }
      private void tableTextCenter(){
             centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
              for (int i = 0; i < todaysTable.getColumnCount(); i++) {
            todaysTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
      }
       private void tableTextCenterv2(){
             centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
              for (int i = 0; i < ThisMonthTable1.getColumnCount(); i++) {
            ThisMonthTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
      }
    public void populateTodaysData(String userId){
        try {
            DefaultTableModel model = (DefaultTableModel)todaysTable.getModel();
            model.setRowCount(0);
            String sql = "SELECT bookRented, SUM(total_rentals) AS total_rentals FROM (SELECT bookRented, COUNT(*) * totalQuantity AS total_rentals FROM popularbooks WHERE userId = ? AND dateRented = CURDATE() GROUP BY bookRented, totalQuantity) AS subquery GROUP BY bookRented;";
            p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, userId);
            
            rs = p.executeQuery();
            while (rs.next()) {                
                Vector v = new Vector();
                for (int i = 0; i < 36; i++) {
                    v.add(rs.getString("bookRented"));
                    v.add(rs.getInt("total_Rentals"));
                }
                model.addRow(v);
            }
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
    }
      public void populateThiMonthsData(String userId){
        try {
            DefaultTableModel model = (DefaultTableModel)ThisMonthTable1.getModel();
            model.setRowCount(0);
            String sql = "SELECT bookRented, SUM(total_rentals) AS total_rentals FROM (SELECT bookRented, COUNT(*) * totalQuantity AS total_rentals FROM popularbooks WHERE userId = ? GROUP BY bookRented, totalQuantity) AS subquery GROUP BY bookRented";
            p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, userId);
            
            rs = p.executeQuery();
            while (rs.next()) {                
                Vector v = new Vector();
                for (int i = 0; i < 36; i++) {
                    v.add(rs.getString("bookRented"));
                    v.add(rs.getInt("total_Rentals"));
                }
                model.addRow(v);
            }
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
  
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new library.components.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        todaysTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        ThisMonthTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setRoundBottomLeft(40);
        roundPanel1.setRoundBottomRight(40);
        roundPanel1.setRoundTopLeft(40);
        roundPanel1.setRoundTopRight(40);

        todaysTable.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        todaysTable.setForeground(new java.awt.Color(51, 51, 51));
        todaysTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null}
            },
            new String [] {
                "Book Title", "Total Rented"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        todaysTable.setAutoscrolls(false);
        todaysTable.setRowHeight(50);
        jScrollPane1.setViewportView(todaysTable);
        if (todaysTable.getColumnModel().getColumnCount() > 0) {
            todaysTable.getColumnModel().getColumn(0).setResizable(false);
            todaysTable.getColumnModel().getColumn(1).setResizable(false);
        }

        ThisMonthTable1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        ThisMonthTable1.setForeground(new java.awt.Color(51, 51, 51));
        ThisMonthTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null}
            },
            new String [] {
                "Book Title", "Total Rented"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ThisMonthTable1.setRowHeight(50);
        jScrollPane3.setViewportView(ThisMonthTable1);
        if (ThisMonthTable1.getColumnModel().getColumnCount() > 0) {
            ThisMonthTable1.getColumnModel().getColumn(0).setResizable(false);
            ThisMonthTable1.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Popular Today Rented");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Popular This Month Rented");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(105, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ThisMonthTable1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private library.components.RoundPanel roundPanel1;
    private javax.swing.JTable todaysTable;
    // End of variables declaration//GEN-END:variables
}
