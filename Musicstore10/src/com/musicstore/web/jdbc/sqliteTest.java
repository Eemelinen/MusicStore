package com.musicstore.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class sqliteTest {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		String sql = "SELECT * FROM Album WHERE ArtistId = (SELECT ArtistId FROM Artist WHERE Name = ?)";
		
		Scanner scanner = new Scanner(System.in);
		
		databaseConnection db = new databaseConnection();
				
		Connection conn = db.connect();
		PreparedStatement statement = conn.prepareStatement(sql);
		
		System.out.print("Write Artist Name:");
		
		String artistName = scanner.nextLine();
		
		statement.setString(1, artistName);
		ResultSet results = statement.executeQuery();
		
		System.out.println("Found albums:");
		while(results.next()) {
			
			System.out.println(results.getString("Title"));
			
		}
		
		db.close(results, statement, conn);
		scanner.close();
	}
}
