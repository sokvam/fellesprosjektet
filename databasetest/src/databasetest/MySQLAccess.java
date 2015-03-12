package databasetest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MySQLAccess {
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;

  public void readDataBase() throws Exception {
    try {
      // This will load the MySQL driver, each DB has its own driver
      Class.forName("com.mysql.jdbc.Driver");
      // Setup the connection with the DB
      connect = DriverManager
          .getConnection("jdbc:mysql://mysql.stud.ntnu.no/tallakb_test?"
              + "user=tallakb_test");

      // Statements allow to issue SQL queries to the database
      statement = connect.createStatement();
      // Result set get the result of the SQL query
      resultSet = statement
          .executeQuery("select * from BOK");
      writeResultSet(resultSet);

      // PreparedStatements can use variables and are more efficient
     

      preparedStatement = connect
          .prepareStatement("SELECT * from BOK");
      resultSet = preparedStatement.executeQuery();
      writeResultSet(resultSet);
      
    } catch (Exception e) {
      throw e;
    } finally {
      close();
    }

  }

 
  private void writeResultSet(ResultSet resultSet) throws SQLException {
    // ResultSet is initially before the first data set
    while (resultSet.next()) {
      // It is possible to get the columns via name
      // also possible to get the columns via the column number
      // which starts at 1
      // e.g. resultSet.getSTring(2);
      Integer bokId = resultSet.getInt(1);
      String bokTittel = resultSet.getString(2);
      System.out.println("BokId: " + bokId + " -  Tittel: "+bokTittel);
      
    }
  }

  // You need to close the resultSet
  private void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {

    }
  }

} 