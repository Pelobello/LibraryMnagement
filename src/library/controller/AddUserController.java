
package library.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Date;
import library.database.DatabaseConnection;
import library.model.ModelUserData;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;


public class AddUserController {
    
    public AddUserController() {
    }
    
    public boolean addUserToDatabase(ModelUserData data){
        try {
           
           
            String sql = "INSERT INTO user_info (userId,libraryName,userName,passWord,birthDate)values(?,?,?,?,?)";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, data.getUserId());
            p.setString(2, data.getLibraryName());
            p.setString(3, data.getUserName());
            p.setString(4, new String(data.getPassWord()));
            java.sql.Date sqlDate = new java.sql.Date(data.getBirthDate().getTime());
            p.setDate(5, sqlDate);
            
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
                  return new ModelUserData(rs.getString("userId"), rs.getString("libraryName"), rs.getString("userName"), rs.getString("passWord").toCharArray(), rs.getDate("birthDate"));
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
