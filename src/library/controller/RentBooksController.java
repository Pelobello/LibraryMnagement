/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import library.database.DatabaseConnection;


public class RentBooksController {

    /**
     * @return the bookPrice
     */
    public double getBookPrice() {
        return bookPrice;
    }

    /**
     * @param bookPrice the bookPrice to set
     */
    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    /**
     * @return the ctr
     */
    public String getCtr() {
        return ctr;
    }

    /**
     * @param ctr the ctr to set
     */
    public void setCtr(String ctr) {
        this.ctr = ctr;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    
    public String getBookName() {
        return bookName;
    }

   
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

  
    public String getCustomerFirstName() {
        return customerFirstName;
    }

   
    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    
    public String getCustomerLastName() {
        return customerLastName;
    }

  
    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

   
    public Date getDate() {
        return date;
    }

  
    public void setDate(Date date) {
        this.date = date;
    }

   
    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    
    public double getTotalAmount() {
        return totalAmount;
    }

    
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

   
    public int getTotalQuantity() {
        return totalQuantity;
    }

   
    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public RentBooksController() {
    }
    public void rentBooksToDatabase()throws SQLException, IOException, ClassNotFoundException{
        try {
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            java.sql.Date sqlReturnDate = new java.sql.Date(returnDate.getTime());

            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement("insert into customer_rented_books_data(userId,bookRented,firstName,lastName,dateRented,dateReturn,totalAmount,totalQuantity,ctr,bookPrice)values(?,?,?,?,?,?,?,?,?,?)");
            p.setString(1, userId);
            p.setString(2, bookName);
            p.setString(3, customerFirstName);
            p.setString(4, customerLastName);
            p.setDate(5, sqlDate);
            p.setDate(6, sqlReturnDate);
            p.setInt(7, (int) totalAmount);
            p.setInt(8, totalQuantity);
            p.setString(9, ctr);
            p.setInt(10, (int) bookPrice);
            
            p.execute();
            
        } catch (Exception e) {
             e.printStackTrace();
        }
    }
     public void rentBooksToDatabaseV2()throws SQLException, IOException, ClassNotFoundException{
        try {
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            java.sql.Date sqlReturnDate = new java.sql.Date(returnDate.getTime());

            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement("insert into customer_rented_books_data_v2(userId,ctr,bookRented,firstName,lastName,dateRented,dateReturn,totalAmount,totalQuantity,bookPrice)values(?,?,?,?,?,?,?,?,?,?)");
            p.setString(1, userId);
            p.setString(2, ctr);
            p.setString(3, bookName);
            p.setString(4, customerFirstName);
            p.setString(5, customerLastName);
            p.setDate(6, sqlDate);
            p.setDate(7, sqlReturnDate);
            p.setInt(8, (int) totalAmount);
            p.setInt(9, totalQuantity);
            p.setInt(10, (int) bookPrice);
            
            p.execute();
            
        } catch (Exception e) {
             e.printStackTrace();
        }
    }
      public void addtoPopular()throws SQLException, IOException, ClassNotFoundException{
        try {
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            java.sql.Date sqlReturnDate = new java.sql.Date(returnDate.getTime());

            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement("insert into popularbooks(userId,ctr,bookRented,firstName,lastName,dateRented,dateReturn,totalAmount,totalQuantity,bookPrice)values(?,?,?,?,?,?,?,?,?,?)");
            p.setString(1, userId);
            p.setString(2, ctr);
            p.setString(3, bookName);
            p.setString(4, customerFirstName);
            p.setString(5, customerLastName);
            p.setDate(6, sqlDate);
            p.setDate(7, sqlReturnDate);
            p.setInt(8, (int) totalAmount);
            p.setInt(9, totalQuantity);
            p.setInt(10, (int) bookPrice);
            
            p.execute();
            
        } catch (Exception e) {
             e.printStackTrace();
        }
    }

    public RentBooksController(String userId, String ctr, String bookName, String customerFirstName, String customerLastName, Date date, Date returnDate, double totalAmount, double bookPrice, int totalQuantity) {
        this.userId = userId;
        this.ctr = ctr;
        this.bookName = bookName;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.date = date;
        this.returnDate = returnDate;
        this.totalAmount = totalAmount;
        this.bookPrice = bookPrice;
        this.totalQuantity = totalQuantity;
    }

 
    private String userId;
    private String ctr;
    private String bookName;
    private String customerFirstName;
    private String customerLastName;
    private Date date;
    private Date returnDate;
    private double totalAmount;
    private double bookPrice;
    private int totalQuantity;
    
}
