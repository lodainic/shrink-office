package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManageDatabase {
   private static final String DB_URL = "jdbc:mysql://localhost:3306/database_name";
   private static final String USER = "";
   private static final String PASS = "";
   private Connection conn;
   private Statement stmt;
   public ManageDatabase() {
      // Constructor
   }
   public void openDB() throws  SQLException {
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
   }
   public void executeU(String sql) throws SQLException{
      stmt = conn.createStatement();
      stmt.executeUpdate(sql); 
   }
   public ResultSet executeQ(String sql) throws SQLException {
      stmt = conn.createStatement();
      return stmt.executeQuery(sql);
   }
   public void closeDB() throws SQLException {
      stmt.close();
      conn.close();
   }
}
