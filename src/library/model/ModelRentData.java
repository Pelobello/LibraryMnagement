
package library.model;


public class ModelRentData {

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    
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

    public ModelRentData(String bookTitle, int quantity, int price) {
        this.bookTitle = bookTitle;
        this.quantity = quantity;
        this.price = price;
    }

  
    
    private String bookTitle;
    private int quantity;
    private int price;
}
