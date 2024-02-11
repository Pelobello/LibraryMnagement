/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import library.database.DatabaseConnection;
import library.forms.AddBooks;
import library.forms.MyLibrary;
import library.main.Main;
import library.model.ModelItem;


public class AddBooksController {

public String getAdduserId() {
        return adduserId;
    }

   
    public void setAdduserId(String adduserId) {
        this.adduserId = adduserId;
    }

  
    public String getAddBookTitle() {
        return addBookTitle;
    }

  
    public void setAddBookTitle(String addBookTitle) {
        this.addBookTitle = addBookTitle;
    }

 
    public String getAddBookAuthor() {
        return addBookAuthor;
    }

  
    public void setAddBookAuthor(String addBookAuthor) {
        this.addBookAuthor = addBookAuthor;
    }

  
    public String getAddPublisher() {
        return addPublisher;
    }

   
    public void setAddPublisher(String addPublisher) {
        this.addPublisher = addPublisher;
    }

   
    public String getAddPublicationDate() {
        return addPublicationDate;
    }

   
    public void setAddPublicationDate(String addPublicationDate) {
        this.addPublicationDate = addPublicationDate;
    }

   
    public String getAddBookDescription() {
        return addBookDescription;
    }

  
    public void setAddBookDescription(String addBookDescription) {
        this.addBookDescription = addBookDescription;
    }

    
    public String getAddBookCategory() {
        return addBookCategory;
    }

   
    public void setAddBookCategory(String addBookCategory) {
        this.addBookCategory = addBookCategory;
    }

   
    public String getAddLanguage() {
        return addLanguage;
    }

    
    public void setAddLanguage(String addLanguage) {
        this.addLanguage = addLanguage;
    }

   
    public String getAddFormat() {
        return addFormat;
    }

    public void setAddFormat(String addFormat) {
        this.addFormat = addFormat;
    }

    public String getAddEdition() {
        return addEdition;
    }

    public void setAddEdition(String addEdition) {
        this.addEdition = addEdition;
    }

    public int getAddPageCount() {
        return addPageCount;
    }
    public void setAddPageCount(int addPageCount) {
        this.addPageCount = addPageCount;
    }

    public int getAddQuantity() {
        return addQuantity;
    }

    public void setAddQuantity(int addQuantity) {
        this.addQuantity = addQuantity;
    }

    public Icon getAddCoverImage() {
        return addCoverImage;
    }

    public void setAddCoverImage(Icon addCoverImage) {
        this.addCoverImage = addCoverImage;
    }

    public AddBooksController(String adduserId, String addBookTitle, String addBookAuthor, String addPublisher, String addPublicationDate, String addBookDescription, String addBookCategory, String addLanguage, String addFormat, String addEdition, int addPageCount, int addQuantity, Icon addCoverImage) {
        this.adduserId = adduserId;
        this.addBookTitle = addBookTitle;
        this.addBookAuthor = addBookAuthor;
        this.addPublisher = addPublisher;
        this.addPublicationDate = addPublicationDate;
        this.addBookDescription = addBookDescription;
        this.addBookCategory = addBookCategory;
        this.addLanguage = addLanguage;
        this.addFormat = addFormat;
        this.addEdition = addEdition;
        this.addPageCount = addPageCount;
        this.addQuantity = addQuantity;
        this.addCoverImage = addCoverImage;
    }

    private byte[] convertImageIconToByteArray(Icon icon) throws IOException {
    BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
    icon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ImageIO.write(bufferedImage, "png", outputStream);

    return outputStream.toByteArray();
}
    private PopulateBooksController populatedata;
   private MyLibrary library;
  
    public AddBooksController(Main main) {
          populatedata = new PopulateBooksController();
       
    }
     public void addBookToDatabase() throws SQLException, IOException, ClassNotFoundException {
    Icon picIcon = addCoverImage;
    byte[] imageBytes = convertImageIconToByteArray(picIcon);
    ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);

  
    
    try (
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(
            "insert into library_data (userId,BTitle,BAuthor,Publisher,PublicationDate,BookDescription,BookCategory,Language,Format,Edition,PageCount,Quantity,CoverImage)values(?,?,?,?,?,?,?,?,?,?,?,?,?)")) {
        
      
        p.setString(1, adduserId);
        p.setString(2, addBookTitle);
        p.setString(3, addBookAuthor);
        p.setString(4, addPublisher);
        p.setString(5, addPublicationDate);
        p.setString(6, addBookDescription);
        p.setString(7, addBookCategory);
        p.setString(8, addLanguage);
        p.setString(9, addFormat);
        p.setString(10, addEdition);
        p.setInt(11, addPageCount);
        p.setInt(12, addQuantity);
        p.setBlob(13, inputStream);
       
      
        p.execute();

    }finally {
        inputStream.close();
    }
    
}

    private String adduserId;
    private String addBookTitle;
    private String addBookAuthor;
    private String addPublisher;
    private String addPublicationDate;
    private String addBookDescription;
    private String addBookCategory;
    private String addLanguage;
    private String addFormat;
    private String addEdition;
    private int addPageCount;
    private int addQuantity;
    private Icon addCoverImage;
    
}
