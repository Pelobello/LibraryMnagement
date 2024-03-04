
package library.controller;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import library.database.DatabaseConnection;


public class Update_Delete_CustomerData {
    private PreparedStatement p;
    public Update_Delete_CustomerData() {
    }
   
    public void DeleteCustomerData(AddCustomerController data){
        try {
        String sql = "DELETE FROM custumer_data WHERE id = ?";
        p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
        p.setInt(1, data.getIdNumber());
        p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }         
    }
    public void UpdateCustomerData(AddCustomerController datav2){
        try {
            String sql = "UPDATE custumer_data SET lastName = ?, firstName = ?, birthDate = ?, age = ?, contactNumber = ?, country = ?, province = ?, city = ?, barangay = ?, postalCode = ? WHERE id = ?";
            p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, datav2.getLastName());
            p.setString(2, datav2.getFirstName());
            p.setString(3, datav2.getBirthDate());
            p.setString(4, datav2.getAge());
            p.setString(5, datav2.getContactNumber());
            p.setString(6, datav2.getStreet());
            p.setString(7, datav2.getProvince());
            p.setString(8, datav2.getCity());
            p.setString(9, datav2.getBarangay());
            p.setString(10, datav2.getPostCode());
            p.setInt(11, datav2.getIdNumber());
            
            p.executeUpdate();
                
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
      public boolean alreadyExistingCustomer(AddCustomerController dataExisting){
        try {
            String sql = "SELECT * FROM custumer_data WHERE cUID = ? AND (lastName = ? OR firstName = ?)ORDER BY cUID";
            p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, dataExisting.getUserId());
            p.setString(2, dataExisting.getLastName());
            p.setString(3, dataExisting.getFirstName());
            ResultSet rs = p.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
