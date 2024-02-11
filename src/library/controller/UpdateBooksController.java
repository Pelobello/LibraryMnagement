
package library.controller;
import java.sql.PreparedStatement;

import library.database.DatabaseConnection;

public class UpdateBooksController {

    public UpdateBooksController() {
    }
    public void UpdateBooksQuantity(String userId,String bookTitle, String updatedQuantity){
        
        try {
            String sql = "UPDATE library_data SET Quantity = ? WHERE userId = ? AND BTitle = ?";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, updatedQuantity);
            p.setString(2, userId);
            p.setString(3, bookTitle);
      
            p.executeUpdate();
       
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
}
