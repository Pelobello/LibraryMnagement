/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.model;

import java.util.Date;


public class ModelNotification {

    
    public int getNumberOfData() {
        return numberOfData;
    }

   
    public void setNumberOfData(int numberOfData) {
        this.numberOfData = numberOfData;
    }

   
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

 
    public String getLastname() {
        return lastname;
    }

 
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

   
    public String getBooktitle() {
        return booktitle;
    }

 
    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public ModelNotification(String name, String lastname, String booktitle, Date returnDate) {
        this.name = name;
        this.lastname = lastname;
        this.booktitle = booktitle;
        this.returnDate = returnDate;
    }

    public ModelNotification() {
    }
    private String name;
    private String lastname;
    private String booktitle;
    private Date returnDate;
    private int numberOfData;
}
