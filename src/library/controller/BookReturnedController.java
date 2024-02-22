
package library.controller;

import library.database.DatabaseConnection;
import java.sql.PreparedStatement;


public class BookReturnedController {
    private PreparedStatement p;
    public BookReturnedController() {
    }
    public void returnBook(String bookTitle,int quantity){
        try {
            String sql = "UPDATE library_data SET Quantity = Quantity + ? WHERE BTitle LIKE ?";
             p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setInt(1, quantity);
            p.setString(2, bookTitle);
            
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteBookRented(String ctr){
        try {
            String sql = "DELETE FROM customer_rented_books_data_v2 WHERE ctr LIKE ?";
            p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, ctr);
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
