/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import library.database.DatabaseConnection;
import library.forms.RenterData;
import library.model.ModelRenter;


public class PopulateRenterController {

    private RenterData renter;
    public PopulateRenterController(RenterData renter) {
       this.renter = renter;
        
    }
    public void populateData(String userId){
        try {
             String sql = "SELECT * FROM customer_rented_books_data_v2 WHERE userId = ?";
          
                    PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
                    p.setString(1, userId);
                    ResultSet rs = p.executeQuery();
                    
                    while (rs.next()) {                

                  renter.addRenter(new ModelRenter(rs.getString("ctr"),
                          rs.getString("firstName"),
                          rs.getString("lastName"),
                          rs.getString("bookRented"),
                          rs.getInt("bookPrice"),
                          rs.getInt("totalAmount"),
                          rs.getInt("totalQuantity"), 
                          rs.getDate("dateRented"), 
                          rs.getDate("dateReturn")));
                        
                        
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public void SearchRenter(String uId,String searchTextField){
        try {
            
            String sql = "SELECT * FROM customer_rented_books_data_v2 WHERE userId = ? And ctr LIKE ? ";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, uId);
            p.setString(2, "%" +searchTextField+ "%");
//            p.setString(3, "%" +searchTextField+ "%");
           
            ResultSet rs = p.executeQuery();
            while (rs.next()) {                
                renter.addRenter(new ModelRenter(rs.getString("ctr"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("bookRented"),
                        rs.getInt("bookPrice"),
                        rs.getInt("totalAmount"),
                        rs.getInt("totalQuantity"),
                        rs.getDate("dateRented"),
                        rs.getDate("dateReturn")));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
}
