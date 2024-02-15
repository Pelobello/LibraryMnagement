
package library.model;

import java.util.Date;


public class ModelRenter {

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

  
    public String getCtr() {
        return ctr;
    }

 
    public void setCtr(String ctr) {
        this.ctr = ctr;
    }

  
    public String getFirstName() {
        return firstName;
    }

   
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

  
    public String getLastName() {
        return lastName;
    }

   
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getBookTitle() {
        return bookTitle;
    }

   
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

   
    public int getBookPrice() {
        return bookPrice;
    }

   
    public void setBookPrice(int bookPrice) {
        this.bookPrice = bookPrice;
    }

   
    public int getTotalPaidl() {
        return totalPaidl;
    }

   
    public void setTotalPaidl(int totalPaidl) {
        this.totalPaidl = totalPaidl;
    }

   
    public Date getDateRented() {
        return dateRented;
    }

   
    public void setDateRented(Date dateRented) {
        this.dateRented = dateRented;
    }

   
    public Date getReturnDate() {
        return returnDate;
    }

  
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public ModelRenter() {
    }

    public ModelRenter(String ctr, String firstName, String lastName, String bookTitle, int bookPrice, int totalPaidl, int quantity, Date dateRented, Date returnDate) {
        this.ctr = ctr;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookTitle = bookTitle;
        this.bookPrice = bookPrice;
        this.totalPaidl = totalPaidl;
        this.quantity = quantity;
        this.dateRented = dateRented;
        this.returnDate = returnDate;
    }

   
    
 
    
    private String ctr;
    private String firstName;
    private String lastName;
    private String bookTitle;
    private int bookPrice;
    private int totalPaidl;
    private int quantity;
    private Date dateRented;
    private Date returnDate;
   
    
}
