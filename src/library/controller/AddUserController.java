
package library.controller;

import java.sql.Date;
import library.database.DatabaseConnection;
import library.model.ModelUserData;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;


public class AddUserController {
    
    public AddUserController() {
    }
    
    public void addUserToDatabase(ModelUserData data){
        try {
           
           
            String sql = "INSERT INTO user_info (userId,libraryName,userName,passWord,birthDate)values(?,?,?,?,?)";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, data.getUserId());
            p.setString(2, data.getLibraryName());
            p.setString(3, data.getUserName());
            p.setString(4, data.getPassWord());
            java.sql.Date sqlDate = new java.sql.Date(data.getBirthDate().getTime());
            p.setDate(5, sqlDate);
            
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   
}
