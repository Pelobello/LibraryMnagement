
package library.model;


public class ModelRentData {

    
    public String getBookTitle() {
        return bookTitle;
    }

   
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

   
    public int getQuantity() {
        return quantity;
    }

   
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ModelRentData() {
    }

    public ModelRentData(String bookTitle, int quantity) {
        this.bookTitle = bookTitle;
        this.quantity = quantity;
    }
    
    private String bookTitle;
    private int quantity;
}
