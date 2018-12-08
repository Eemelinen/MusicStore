package com.musicstore.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArtistDao {
	
	public List<Artist> getAllArtists() throws ClassNotFoundException, SQLException {
		
		databaseConnection db = new databaseConnection();
		
		Connection conn = db.connect();
		PreparedStatement statement = null;
		ResultSet results = null;
		
		List<Artist> artistList = new ArrayList<>();
		
		try {
			
			statement = conn.prepareStatement("SELECT * FROM Artist ORDER BY Name ASC");
			
			results = statement.executeQuery();
		
			while (results.next()) {
				
				long id = results.getLong("ArtistId");
				String name = results.getString("Name");
				artistList.add(new Artist(id, name));
				
			}
			
		} catch(SQLException e) {
			
			throw new RuntimeException(e);
			
		} finally {
			
			db.close(results, statement, conn);
		}
		
		return artistList;	
	}
	
	public List<Artist> findArtistById(long id) throws ClassNotFoundException, SQLException {
		
		databaseConnection db = new databaseConnection();
		
		Connection conn = db.connect();
		PreparedStatement statement = null;
		ResultSet results = null;
		
		List<Artist> artistList = new ArrayList<>();
		
		try {
			
			statement = conn.prepareStatement("SELECT * FROM Artist WHERE ArtistId = ?");
			
			statement.setLong(1, id);
			
			results = statement.executeQuery();
		
			while (results.next()) {
				
				long artistId = results.getLong("ArtistId");
				String artistName = results.getString("Name");
				artistList.add(new Artist(artistId, artistName));
				
			}
			
		} catch(SQLException e) {
			
			throw new RuntimeException(e);
			
		} finally {
			
			db.close(results, statement, conn);
		}
		
		return artistList;			
				
	}

	
	public List<Artist> findArtistByName(String name) throws ClassNotFoundException, SQLException {
		
		databaseConnection db = new databaseConnection();
		
		Connection conn = db.connect();
		PreparedStatement statement = null;
		ResultSet results = null;
		
		List<Artist> artistList = new ArrayList<>();
		
		try {
			
			statement = conn.prepareStatement("SELECT * FROM Artist WHERE Name = ?");
			
			statement.setString(1, name);
			
			results = statement.executeQuery();
		
			while (results.next()) {
				
				long id = results.getLong("ArtistId");
				String artistName = results.getString("Name");
				artistList.add(new Artist(id, artistName));
				
			}
			
		} catch(SQLException e) {
			
			throw new RuntimeException(e);
			
		} finally {
			
			db.close(results, statement, conn);
		}
		
		return artistList;	
	}
	
	public void addArtist(Artist artist) throws ClassNotFoundException, SQLException {
				
		databaseConnection db = new databaseConnection();
		
		Connection conn = db.connect();
		PreparedStatement statement = null;
		ResultSet generatedKeys = null;
		
		try {
			
			statement = conn.prepareStatement(
					"INSERT INTO Artist (Name)  Values (?)", Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, artist.getName());
			statement.executeUpdate();
			
			generatedKeys = statement.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				
				long id = generatedKeys.getLong(1);
				artist.setId(id);
			}
			
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
			
		} finally {
			
			db.close(generatedKeys, statement, conn);
			
		}
	}
}
