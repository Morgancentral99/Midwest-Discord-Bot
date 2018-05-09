package me.morgancentral99.midwest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {
	
    private String host, database, username, password;
    private int port;
    Statement statement = null;
	
	private Connection conn = null;
	
	public void onStart() {
        host = "localhost";
        port = 3306;
        database = "essentialmode";
        username = "root";
        password = "1charlie";   

        	try {
				openConnection();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	try {
				Statement statement = conn.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 
	}
	
	  public void openConnection() throws SQLException, ClassNotFoundException {
		    if (conn != null && !conn.isClosed()) {
		        return;
		    }
		 
		    synchronized (this) {
		        if (conn != null && !conn.isClosed()) {
		            return;
		        }
		        Class.forName("com.mysql.jdbc.Driver");
		        conn = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password);
		    }
		}
	  
	  public ResultSet readFromDatabase(String query) {
		  try {
			if(conn.isClosed()) {
				  onStart();
			  }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  try {
			ResultSet set = statement.executeQuery(query);
			return set;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	  }

	  public void executeToDatabase(String Query) {
		  try {
			if(conn.isClosed()) {
				  onStart();
			  }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  try {
			statement.executeUpdate(Query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
}
