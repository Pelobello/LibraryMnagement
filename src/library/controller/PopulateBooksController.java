
package library.controller;


import library.database.DatabaseConnection;
import library.forms.MyLibrary;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import library.forms.AddBooks;
import library.main.Main;
import library.model.ModelItem;


public class PopulateBooksController {
    private MyLibrary library;
    
    public PopulateBooksController(MyLibrary library) {
        this.library = library;
       
    }

    private AddBooks addBooks;
    public PopulateBooksController() {
        
    }
    public void searchBooks(String userId,String titleTextField){
        try {
            String sql = "SELECT * FROM library_data WHERE userId = ? AND BTitle LIKE ?";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, userId);
            p.setString(2, "%" + titleTextField + "%");
            ResultSet rs = p.executeQuery();
            while (rs.next()) {                
                 byte[] blobData = rs.getBytes("CoverImage");
            ImageIcon icon = new ImageIcon(blobData);
         
                   library.addBooks(new ModelItem(
                           rs.getString("BTitle")
                           , rs.getString("BAuthor")
                           , rs.getString("Publisher")
                           , rs.getString("PublicationDate")
                           , rs.getString("BookDescription")
                           , rs.getString("BookCategory"),
                           rs.getString("Language"),
                           rs.getString("Format"),
                           rs.getString("Edition"), 
                           rs.getInt("PageCount"),
                           rs.getInt("Quantity"), 
                           rs.getInt("price"),
                           (icon)));
                  
            }
      
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void populate(String userId){
         
       
         
        
        try {
           String sql = "SELECT * FROM library_data WHERE userId = ?";
          
                    PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
                     
                p.setString(1, userId);
                ResultSet rs = p.executeQuery();
    
            while (rs.next()) {                
                
                 byte[] blobData = rs.getBytes("CoverImage");
            ImageIcon icon = new ImageIcon(blobData);
       
                 library.addBooks(new ModelItem(
                           rs.getString("BTitle")
                           , rs.getString("BAuthor")
                           , rs.getString("Publisher")
                           , rs.getString("PublicationDate")
                           , rs.getString("BookDescription")
                           , rs.getString("BookCategory"),
                           rs.getString("Language"),
                           rs.getString("Format"),
                           rs.getString("Edition"), 
                           rs.getInt("PageCount"),
                           rs.getInt("Quantity"), 
                           rs.getInt("price"),
                           (icon)));
 
                }     
  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
