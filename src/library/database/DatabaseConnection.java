
package library.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;


public class DatabaseConnection {

    
    private static DatabaseConnection instance;
    private Connection connection;
    public static DatabaseConnection getInstance(){
        if (instance==null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    private DatabaseConnection(){
        
    }
    public void ConnectToDatabase() throws  SQLException, ClassNotFoundException{
        String server = "127.0.0.1";
        String port = "3306";
        String database ="library_management_data";
        String user = "root";
        String password = "Gwapoko123";
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = (Connection) java.sql.DriverManager.getConnection("jdbc:mysql://"+server+":"+port+"/"+database,user,password);
    }
    public Connection getConnection() {
        return connection;
    }
}
