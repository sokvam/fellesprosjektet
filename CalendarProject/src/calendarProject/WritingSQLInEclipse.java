// dette er bare eksempelkode for hvordan man håndterer databasene i eclipse. Det er totalt useless for dere uten riktig connection og databaser,
// men illustrerer godt hvordan det fungerer
package calendarProject;
import java.sql.*;

public class WritingSQLInEclipse {
	
	//alternativ constructør, avhengig av om vi vil ha databasene på flere eller kun en pc
	//public void constructer(String DB_URL, String USER, String PASS){
		//this.USER = USER;
		//this.PASS = PASS;
		//this.DB_URL = "jdbc:mysql://" + DB_URL;
	//}

	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://127.0.0.1:3306"; //127.0.0.1:3306 er lokaladressen til min connection og vil derfor variere, den finner man i workbench
	   static final String USER = "root"; //min bruker
	   static final String PASS = "qwertyuiop"; //mitt passord
	   
	   public static void main(String[] args) {
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

	      //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      String sql;
	      String sql2;
	      sql = "SELECT id, title, release_date FROM dvd_collection.movies";
	      sql2 = "insert into dvd_collection.movies values(null, 'Hangover15', '2032-06-12')";
	      stmt.executeUpdate(sql2);
	      ResultSet rs = stmt.executeQuery(sql);

	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         int id  = rs.getInt("id");
	         String title = rs.getString("title");
	         Date date = rs.getDate("release_date");

	         //Display values
	         System.out.print("ID: " + id);
	         System.out.print(", title: " + title);
	         System.out.print(", release date: " + date);
	         System.out.print("\n");
	      }
	      //STEP 6: Clean-up environment
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
	}//end main
}//end FirstExample
	
