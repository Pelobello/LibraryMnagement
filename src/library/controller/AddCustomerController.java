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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
    private AddCustomerController addcustomer;
    public AddCustomerController() {
        
        
    }

    public AddCustomerController(String lastName, String firstName, String birthDate, String age, String contactNumber, String country, String province, String city, String barangay, String postCode) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.age = age;
        this.contactNumber = contactNumber;
        this.country = country;
        this.province = province;
        this.city = city;
        this.barangay = barangay;
        this.postCode = postCode;
    }
    public void AddCustomerToDatabase()throws SQLException, IOException, ClassNotFoundException{
        try {
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement
        ("insert into custumer_data (lastName,firstName,birthDate,age,contactNumber,country,province,city,barangay,postalCode)values(?,?,?,?,?,?,?,?,?,?)");
            
            p.setString(1, lastName);
            p.setString(2, firstName);
            p.setString(3, birthDate);
            p.setString(4, age);
            p.setString(5, contactNumber);
            p.setString(6, country);
            p.setString(7, province);
            p.setString(8, city);
            p.setString(9, barangay);
             p.setString(10, postCode);
            
            p.executeUpdate();
            
            
            
        } catch (Exception e) {
             e.printStackTrace();
        }
    }
    

    private String lastName;
     private String firstName;
      private String birthDate;
       private String age;
        private String contactNumber;
         private String country;
          private String province;
          private String city;
          private String barangay;
          private String postCode;
        
        
      
}
