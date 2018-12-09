package com.musicstore.web.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class databaseConnection {
	
	private static final String URL = "jdbc:sqlite:C:\\sqlite\\Chinook_Sqlite.sqlite";
	
	public Connection connect() throws SQLException, ClassNotFoundException {
		
		Class.forName("org.sqlite.JDBC");
		
		return DriverManager.getConnection(URL);	
	}

	public void close(ResultSet results, PreparedStatement statement, Connection conn) {
		
		try {
			
			if (results != null) {
				results.close();
			}
			
			if (statement != null) {
				statement.close();
			}
						
			//Yhteyttä ei varsinaisesti suljeta vaan se palautetaan connection pooliin.
			if (conn != null) {
				conn.close();
			}	
			
		} catch (Exception exc) {
			exc.printStackTrace();	
		}	
	}
}
