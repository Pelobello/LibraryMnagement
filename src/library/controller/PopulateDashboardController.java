/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import library.chart.ModelChart;
import library.database.DatabaseConnection;
import library.forms.DashBoard;
import library.forms.RenterData;
import library.main.Main;
import library.model.ModelDashboardData;
import library.model.ModelRenter;


public class PopulateDashboardController {
    private DashBoard dashboard;
    private RenterData renter;
    private Main main;

    public PopulateDashboardController() throws SQLException, ClassNotFoundException {
         dashboard = new DashBoard();
         renter = new RenterData();
         
    }
    public void testDataDashBoad(String usId){
      
     try {
        
         List <ModelDashboardData> list= new ArrayList<>();
         DatabaseConnection.getInstance().ConnectToDatabase();
         String sql = "SELECT DATE_FORMAT(MAX(dateRented), '%M') AS `Month`, SUM(totalAmount) AS Amount, SUM(totalQuantity) AS TotalQuantity FROM library_management_data.customer_rented_books_data WHERE userId = ? GROUP BY DATE_FORMAT(dateRented, '%m%Y') ORDER BY MIN(dateRented) DESC LIMIT 12;";
         PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
         p.setString(1, usId);
         ResultSet rs = p.executeQuery();
 
         while (rs.next()) {    
             String month = rs.getString("Month");
             double totalAmount = rs.getDouble("Amount");
             double totalQuantity = rs.getDouble("TotalQuantity");
             
             list.add(new ModelDashboardData(month, totalAmount, totalQuantity));
         }
         rs.close();
         p.close();
         
         for (int i = list.size()-1; i > -1; i--) {
             ModelDashboardData d = list.get(i);
             dashboard.chart.addData(new ModelChart(d.getMonth(), new double[]{d.getTotalAmount(),d.getTotalQuantity()}));
             
         }
      dashboard.chart.start();
     } catch (Exception e) {
         e.printStackTrace();
     }
 }
    
    

    
}
