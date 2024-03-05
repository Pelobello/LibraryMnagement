
package library.controller;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Date;
import library.database.DatabaseConnection;
import library.model.ModelUserData;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;


public class AddUserController {
    
    public AddUserController() {
    }
   
      private byte[] convertImageIconToByteArray(Icon icon) throws IOException {
    BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
    icon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ImageIO.write(bufferedImage, "png", outputStream);

    return outputStream.toByteArray();
}
    public boolean addUserToDatabase(ModelUserData data){
        try {
           Icon picIcon = data.getImageAvatar();
    byte[] imageBytes = convertImageIconToByteArray(picIcon);
    ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
           
            String sql = "INSERT INTO user_info (userId,libraryName,userName,passWord,birthDate,imageAvatar)values(?,?,?,?,?,?)";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, data.getUserId());
            p.setString(2, data.getLibraryName());
            p.setString(3, data.getUserName());
            p.setString(4, new String(data.getPassWord()));
            java.sql.Date sqlDate = new java.sql.Date(data.getBirthDate().getTime());
            p.setDate(5, sqlDate);
            p.setBlob(6,inputStream);
            
            p.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
      public ModelUserData Sign_In(ModelUserData data){
          try {
             
              String sql = "SELECT * FROM user_info WHERE userName LIKE ? AND passWord LIKE ?";
              PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
              p.setString(1, data.getUserName());
              p.setString(2, new String(data.getPassWord()));
              ResultSet rs = p.executeQuery();
               if (rs.next()) {
                     byte[] blobData = rs.getBytes("imageAvatar");
                    ImageIcon icon = new ImageIcon(blobData);
                  return new ModelUserData(rs.getString("userId"), rs.getString("libraryName"), rs.getString("userName"), rs.getString("passWord").toCharArray(), rs.getDate("birthDate"), (icon));
              }else{
                   return null;
               }
          } catch (Exception e) {
              e.printStackTrace();
              return null;
          }
     
      }
   
    
      public String encryptPass(String password){
        try {
            MessageDigest digestor = MessageDigest.getInstance("SHA-256");
            byte [] encodeHash = digestor.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder encryptionValue = new StringBuilder(2 * encodeHash.length);
            
             for (int i = 0; i < encodeHash.length; i++) {
                String hexVal = Integer.toHexString(0xff & encodeHash[i]);
                if (hexVal.length() == 1) {
                    encryptionValue.append('0');
                }
                encryptionValue.append(hexVal);
            }
             return encryptionValue.toString();
        } catch (Exception e) {
             return e.getMessage();
        }
    }
}
