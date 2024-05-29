package DBConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectMySQL {
    public static Connection Conn(){
        Connection con = null;
        // Connect to the MySQL database
        String url = "jdbc:mysql://localhost:3306/world";
        String username = "root";
        String password = "a123";
        try {
            // Load the MySQL JDBC driver
            con = DriverManager.getConnection(url, username, password);
            return con;            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    // main
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection con = Conn();
        // Test connection
        if (con!= null){
            // Create a statement
            Statement statement = con.createStatement();

            // Execute a query
            String query = "SELECT * FROM city";
            ResultSet resultSet = statement.executeQuery(query);

            // Print the results
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String countryCode = resultSet.getString("CountryCode");
                System.out.println("ID: " + id + ", Name: " + name + ", Country Code: "+ countryCode);
            }
            // Close the connection
            resultSet.close();
            statement.close();
            con.close();
        }
        else
            System.out.print("Error! ");
    }
}
