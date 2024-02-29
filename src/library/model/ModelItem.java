
package library.model;

import javax.swing.Icon;


public class ModelItem {

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
    public String getBookAuthor() {
        return bookAuthor;
    }
    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getQuantity() {
        return quantity;
    }

 
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

 
    public Icon getCoverImage() {
        return coverImage;
    }

    public ModelItem() {
    }

    public ModelItem(String bookTitle, String bookAuthor, String publisher, String publicationDate, String bookDescription, String bookCategory, String language, String format, String edition, int pageCount, int quantity, int price, Icon coverImage) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.bookDescription = bookDescription;
        this.bookCategory = bookCategory;
        this.language = language;
        this.format = format;
        this.edition = edition;
        this.pageCount = pageCount;
        this.quantity = quantity;
        this.price = price;
        this.coverImage = coverImage;
    }

    public void setCoverImage(Icon coverImage) {
        this.coverImage = coverImage;
    }

    private String bookTitle;
    private String bookAuthor;
    private String publisher;
    private String publicationDate;
    private String bookDescription;
    private String bookCategory;
    private String language;
    private String format;
    private String edition;
    private int pageCount;
    private int quantity;
    private int price;
    private Icon coverImage;
   
}
