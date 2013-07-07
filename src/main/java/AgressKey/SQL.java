package AgressKey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQL {
	public static void Update(String a,int s) {
		 Connection c = null;
		    Statement stmt = null;
		    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:key.db");
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      String sql = "UPDATE keylist set use = "+s+" where key='"+a+"';";
		      stmt.executeUpdate(sql);

		      stmt.close();
		      c.commit();
		      c.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    }
		  }
	public static void Select(String a,String s) {
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:key.db");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM keylist where key='"+a+"';" );
	      while ( rs.next() ) {
	         System.out.println(rs.getString(s));
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	  }
	public static void remove(String a) {
		 Connection c = null;
		    Statement stmt = null;
		    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:key.db");
		      c.setAutoCommit(false);

		      stmt = c.createStatement();
		      String sql = "DELETE from keylist where key='"+a+"';";
		      stmt.executeUpdate(sql);

		      stmt.close();
		      c.commit();
		      c.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    }
		  }
public static void add(String a,int s,String g) {
	 Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:key.db");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      String sql = "INSERT INTO keylist VALUES (null, "+"'"+a+"'"+", "+s+","+"'"+g+"'"+" );";
	      stmt.executeUpdate(sql);

	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	  }
}
