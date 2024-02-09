/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import library.database.DatabaseConnection;


public class AddCustomerController {

  
    public String getAddCustomerId() {
        return addCustomerId;
    }


    public void setAddCustomerId(String addCustomerId) {
        this.addCustomerId = addCustomerId;
    }


    public String getLastName() {
        return lastName;
    }

 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

 
    public String getFirstName() {
        return firstName;
    }

 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

   
    public String getBirthDate() {
        return birthDate;
    }


    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

   
    public String getAge() {
        return age;
    }

 
    public void setAge(String age) {
        this.age = age;
    }

   
    public String getContactNumber() {
        return contactNumber;
    }


    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

   
    public String getStreet() {
        return street;
    }

  
    public void setStreet(String street) {
        this.street = street;
    }

   
    public String getProvince() {
        return province;
    }

   
    public void setProvince(String province) {
        this.province = province;
    }

  
    public String getCity() {
        return city;
    }

   
    public void setCity(String city) {
        this.city = city;
    }

   
    public String getBarangay() {
        return barangay;
    }

   
    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

   
    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public AddCustomerController(String addCustomerId, String lastName, String firstName, String birthDate, String age, String contactNumber, String street, String province, String city, String barangay, String postCode) {
        this.addCustomerId = addCustomerId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.age = age;
        this.contactNumber = contactNumber;
        this.street = street;
        this.province = province;
        this.city = city;
        this.barangay = barangay;
        this.postCode = postCode;
    }

    public AddCustomerController() {
    }

 
    public void AddCustomerToDatabase()throws SQLException, IOException, ClassNotFoundException{
        try {
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement
        ("insert into custumer_data (customerUserId,lastName,firstName,birthDate,age,contactNumber,country,province,city,barangay,postalCode)values(?,?,?,?,?,?,?,?,?,?,?)");
            p.setString(1, getAddCustomerId());
            p.setString(2, getLastName());
            p.setString(3, getFirstName());
            p.setString(4, getBirthDate());
            p.setString(5, getAge());
            p.setString(6, getContactNumber());
            p.setString(7, getStreet());
            p.setString(8, getProvince());
            p.setString(9, getCity());
            p.setString(10, getBarangay());
             p.setString(11, getPostCode());
            
            p.executeUpdate();
            
            
            
        } catch (Exception e) {
             e.printStackTrace();
        }
    }
    
    private String addCustomerId;
    private String lastName;
     private String firstName;
      private String birthDate;
       private String age;
        private String contactNumber;
         private String street;
          private String province;
          private String city;
          private String barangay;
          private String postCode;
        
        
      
}
