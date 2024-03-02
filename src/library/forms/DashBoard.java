/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package library.forms;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import library.chart.ModelChart;
import library.controller.PopulateBooksController;
import library.controller.PopulateDashboardController;

import library.database.DatabaseConnection;
import library.main.Main;
import library.model.ModelDashboardData;
import library.userId.User_Id_Constructor;


public class DashBoard extends javax.swing.JPanel {
    private Main main;
    private PopulateDashboardController populateDashboard;
    private User_Id_Constructor userIdC;
    private DefaultTableCellRenderer centerRenderer;

        public DashBoard() throws SQLException, ClassNotFoundException {
        initComponents();
        setOpaque(false);
        chart.setTitle("Chart Data");
        chart.addLegend("Total Profit", Color.decode("#211C6A"), Color.decode("#59B4C3"));
        chart.addLegend("Books Rented", Color.black, Color.black);
       calendar1.setBackground(new Color(200,200,200,200));
       centerRenderer = new DefaultTableCellRenderer();
       tableTextCenter();
      
    }
        private void tableTextCenter(){
             centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
              for (int i = 0; i < customerTable.getColumnCount(); i++) {
            customerTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
            
        }
         public void searchRenterData(String userId,String searchTextField) {
            
            try {
                  DefaultTableModel model = (DefaultTableModel)customerTable.getModel();
                  model.setRowCount(0);
            DatabaseConnection.getInstance().ConnectToDatabase();
            String sql = "SELECT * FROM customer_rented_books_data_v2 WHERE userId = ? AND lastName LIKE ?";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, userId);
            p.setString(2, "%"+ searchTextField.trim() +"%");
            ResultSet rs = p.executeQuery();
            while (rs.next()) {                
                Vector v = new Vector();
                for (int i = 0; i < 35; i++) {
                    v.add(rs.getInt("id"));
                    v.add(rs.getString("userId"));
                    v.add(rs.getString("bookRented"));
                    v.add(rs.getString("firstName"));
                    v.add(rs.getString("lastName"));
                    v.add(rs.getDate("dateRented"));
                    v.add(rs.getDate("dateReturn"));
                    v.add(rs.getInt("totalAmount"));
                    v.add(rs.getInt("totalQuantity"));
                }
               model.addRow(v);
            } 
            } catch (Exception e) {
                e.printStackTrace();
            }               
        }
        public void populateRenterData(String userId) throws SQLException, ClassNotFoundException{
            
            try {
                  DefaultTableModel model = (DefaultTableModel)customerTable.getModel();
                  model.setRowCount(0);
            
            String sql = "SELECT * FROM customer_rented_books_data_v2 WHERE userId = ?";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, userId);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {                
                Vector v = new Vector();
                for (int i = 0; i < 35; i++) {
                    v.add(rs.getInt("id"));
                    v.add(rs.getString("userId"));
                    v.add(rs.getString("bookRented"));
                    v.add(rs.getString("firstName"));
                    v.add(rs.getString("lastName"));
                    v.add(rs.getDate("dateRented"));
                    v.add(rs.getDate("dateReturn"));
                    v.add(rs.getInt("totalAmount"));
                    v.add(rs.getInt("totalQuantity"));

                }
               model.addRow(v);
            } 
            } catch (Exception e) {
                e.printStackTrace();
            }
         
                    
        }
        
public void testData(String UI){
     try {
         List <ModelDashboardData> list= new ArrayList<>();
         DatabaseConnection.getInstance().ConnectToDatabase();
         String sql = "SELECT DATE_FORMAT(MAX(dateRented), '%M') AS `Month`, " +
                     "SUM(totalAmount) AS Amount, " +
                     "SUM(totalQuantity) AS TotalQuantity " +
                     "FROM library_management_data.customer_rented_books_data " +
                     "WHERE userId = ? " +
                     "GROUP BY DATE_FORMAT(dateRented, '%m%Y') " +
                     "ORDER BY MIN(dateRented) DESC LIMIT 12;";

        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
        
        // Set the parameter value for the userId
        p.setString(1, UI);
         ResultSet rs = p.executeQuery();
         while (rs.next()) {    
             String month = rs.getString("Month");
             double totalAmount = rs.getDouble("Amount");
             double totalQuantity = rs.getDouble("TotalQuantity");
             
             list.add(new ModelDashboardData(month, totalAmount, totalQuantity));
         }
         rs.close();
         p.close();
         
       for (int i = list.size()-1; i >=0; i--) {
    ModelDashboardData d = list.get(i);
    chart.addData(new ModelChart(d.getMonth(), new double[]{d.getTotalAmount(), d.getTotalQuantity()}));
}
       chart.start();


     } catch (Exception e) {
         e.printStackTrace();
     }
 }

   

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanelR = new library.components.RoundPanel();
        chart = new library.chart.CurveLineChart();
        roundPanel2 = new library.components.RoundPanel();
        roundPanel6 = new library.components.RoundPanel();
        calendar1 = new CalendarUI.calendar.Calendar();
        dataUID = new javax.swing.JLabel();
        roundPanel3 = new library.components.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        customerTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        roundPanelR.setBackground(new java.awt.Color(255, 255, 255));
        roundPanelR.setRoundBottomLeft(40);
        roundPanelR.setRoundBottomRight(40);
        roundPanelR.setRoundTopLeft(40);
        roundPanelR.setRoundTopRight(40);

        chart.setBackground(new java.awt.Color(0, 0, 0));
        chart.setForeground(new java.awt.Color(102, 102, 102));
        chart.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        chart.setTitleFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        javax.swing.GroupLayout roundPanelRLayout = new javax.swing.GroupLayout(roundPanelR);
        roundPanelR.setLayout(roundPanelRLayout);
        roundPanelRLayout.setHorizontalGroup(
            roundPanelRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanelRLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanelRLayout.setVerticalGroup(
            roundPanelRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanelRLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                .addContainerGap())
        );

        roundPanel6.setRoundBottomLeft(40);
        roundPanel6.setRoundBottomRight(40);
        roundPanel6.setRoundTopLeft(40);
        roundPanel6.setRoundTopRight(40);

        calendar1.setBackground(new java.awt.Color(255, 255, 255));
        calendar1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        calendar1.setOpaque(false);

        javax.swing.GroupLayout roundPanel6Layout = new javax.swing.GroupLayout(roundPanel6);
        roundPanel6.setLayout(roundPanel6Layout);
        roundPanel6Layout.setHorizontalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(calendar1, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dataUID, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        roundPanel6Layout.setVerticalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(calendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dataUID)
                .addContainerGap())
        );

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(roundPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        roundPanel3.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel3.setRoundBottomLeft(40);
        roundPanel3.setRoundBottomRight(40);
        roundPanel3.setRoundTopLeft(40);
        roundPanel3.setRoundTopRight(40);

        customerTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        customerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "userId", "Book Title", "First Name", "Last Name", "Date Rented", "Date Return", "Total Amount", "Rented Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        customerTable.setRowHeight(30);
        customerTable.setRowMargin(5);
        jScrollPane1.setViewportView(customerTable);
        if (customerTable.getColumnModel().getColumnCount() > 0) {
            customerTable.getColumnModel().getColumn(0).setMinWidth(0);
            customerTable.getColumnModel().getColumn(0).setMaxWidth(0);
            customerTable.getColumnModel().getColumn(1).setMinWidth(0);
            customerTable.getColumnModel().getColumn(1).setMaxWidth(0);
        }

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(roundPanelR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanelR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CalendarUI.calendar.Calendar calendar1;
    public library.chart.CurveLineChart chart;
    public javax.swing.JTable customerTable;
    public javax.swing.JLabel dataUID;
    private javax.swing.JScrollPane jScrollPane1;
    private library.components.RoundPanel roundPanel2;
    private library.components.RoundPanel roundPanel3;
    private library.components.RoundPanel roundPanel6;
    public library.components.RoundPanel roundPanelR;
    // End of variables declaration//GEN-END:variables
}
