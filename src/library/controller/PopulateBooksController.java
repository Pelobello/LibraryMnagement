
package library.controller;


import library.database.DatabaseConnection;
import library.forms.MyLibrary;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import library.main.Main;
import library.model.ModelItem;


public class PopulateBooksController {
    private MyLibrary library;
    
    public PopulateBooksController(MyLibrary library) {
        this.library = library;
       
    }
    public void populate(){
         
        try {
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement("SELECT * FROM library_data WHERE userId = 142");
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
                           icon));
                   
                }
            SwingUtilities.invokeLater(() -> {
                library.revalidate();
                library.repaint();
            });
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
