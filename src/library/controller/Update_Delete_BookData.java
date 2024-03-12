
package library.controller;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import library.database.DatabaseConnection;
import library.model.ModelItem;
public class Update_Delete_BookData {
    
    private PreparedStatement p;
    public Update_Delete_BookData() {
    }
     private byte[] convertImageIconToByteArray(Icon icon) throws IOException {
    BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
    Graphics g = bufferedImage.createGraphics();
    icon.paintIcon(null, g, 0, 0);
    g.dispose();

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
    return byteArrayOutputStream.toByteArray();
}

      
    
    public void deleteBooksData(ModelItem idData){
        try {
            String sql = "DELETE FROM library_data WHERE id = ?";
            p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setInt(1, idData.getId());
            p.executeUpdate();
             JOptionPane.showMessageDialog(null, "Succesfully Deleted");
        } catch (Exception e) {
        }
    }
      public void updateBookDat(ModelItem data) {
    try {
        Icon picIcon = data.getCoverImage();
    byte[] imageBytes = convertImageIconToByteArray(picIcon);
    ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
        String sql = "UPDATE library_data SET BTitle = ?, BAuthor = ?, Publisher = ?, PublicationDate = ?,BookDescription = ?,BookCategory = ?,Language = ?,Format = ?,Edition = ?,PageCount = ?,Quantity = ?,CoverImage = ?, price = ? WHERE id= ?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
        
        p.setString(1, data.getBookTitle());
        p.setString(2, data.getBookAuthor());
        p.setString(3, data.getPublisher());
        p.setString(4, data.getPublicationDate());
        p.setString(5, data.getBookDescription());
        p.setString(6, data.getBookCategory());
        p.setString(7, data.getLanguage());
        p.setString(8, data.getFormat());
        p.setString(9, data.getEdition());
        p.setInt(10, data.getPageCount());
        p.setInt(11, data.getQuantity());
        p.setBlob(12, inputStream);
        p.setInt(13, data.getPrice());     
        p.setInt(14, data.getId());
       
        p.executeUpdate();
       
          
        

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
