
package DBConnect;

import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;



public class ConnectSQLServer {

    static Connection Conn() throws ClassNotFoundException{
         Connection con = null;
         String url = "jdbc:sqlserver://localhost:1433;databaseName=hello";
         String username = "sa";
         String password = "123";
        try {
           // Load the Microsoft JDBC Driver for SQL Server
           // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           // Establish the connection to the SQL Server database
            con = DriverManager.getConnection(url, username, password);
            //return connection;
        } catch (SQLException ex) {
            System.out.print("Error: "+ex.toString());
            //Logger.getLogger(ConnectSQLServer.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return con;
    }
    // main
   public static void main(String[] args) throws ClassNotFoundException, SQLException{
       Connection conn = Conn(); 
       if (conn!=null){
           System.out.println("Connect SQL Server Success!\n");
           // Statement
           Statement st = conn.createStatement();
           // query
           String sql = "SELECT * FROM sv";
           ResultSet re = st.executeQuery(sql); //ResultSet
           
           // Process the results
            while (re.next()) {
                int id = re.getInt("stt");
                String name = re.getString("hoten");
                int age = re.getInt("namsinh");
                System.out.println("\n ID: " + id + ",\n Name: " + name + ",\n Age: " + age);
            }

            // insert
            String query = "INSERT INTO sv(stt, hoten, namsinh) VALUES (?, ?, ?)";
            PreparedStatement ins = conn.prepareStatement(query);

            ins.setInt(1, 3); // Set the value for 'stt'
            ins.setString(2, "Xuan Thanh"); // Set the value for 'hoten'
            ins.setInt(3, 1980); // Set the value for 'namsinh'

            ins.executeUpdate();
            System.out.println(" * inserted. ");

            // Close the result set, statement, and connection
            ins.close();
            re.close();
            st.close();
            conn.close();
       }
       else 
           System.out.print("Error! ");
   }
    
}
