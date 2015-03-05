//--------------------------------------------------------------------------------------------------------------------
package calendarProject;
import java.sql.*;

public class ConnectToDB {

	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://127.0.0.1:3306"; 
	   static final String USER = "root";
	   static final String PASS = "qwertyuiop"; 
	   private static Connection conn;
	   
	   public static void main(String[] args) {
	   conn = null;
	   try{
	      Class.forName("com.mysql.jdbc.Driver");

	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

	      //STEP 6: Clean-up environment
	      //conn.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }
	}//end main
	   public Connection getConnection() {
		   return ConnectToDB.conn;
	   }
}//end FirstExample
	
