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
			
			statement = conn.prepareStatement("SELECT * FROM Artist ORDER BY ArtistId");
			
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
	
	public Artist findArtistById(long id) throws ClassNotFoundException, SQLException {
		
		databaseConnection db = new databaseConnection();
		
		Connection conn = db.connect();
		PreparedStatement statement = null;
		ResultSet results = null;
		
		try {
			
			statement = conn.prepareStatement("SELECT * FROM Artist WHERE ArtistId = ?");
			
			statement.setLong(1, id);
			
			results = statement.executeQuery();
						
				long artistId = results.getLong("ArtistId");
				String artistName = results.getString("Name");
				
				Artist artist = new Artist(artistId, artistName);
				
				return artist;			
			
		} catch(SQLException e) {
			
			throw new RuntimeException(e);
			
		} finally {
			
			db.close(results, statement, conn);
		}					
	}

	
	public List<Artist> findArtistByName(String name) throws ClassNotFoundException, SQLException {
		
		databaseConnection db = new databaseConnection();
		
		Connection conn = db.connect();
		PreparedStatement statement = null;
		ResultSet results = null;
		
		List<Artist> artistList = new ArrayList<>();
		
		try {
			
			String sql = "SELECT * FROM Artist WHERE lower(Name) like ?";	
			
			statement = conn.prepareStatement(sql);
			
			String alteredName = "%" + name.toLowerCase() + "%";
			
			statement.setString(1, alteredName);
			
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

	public void changeArtistName(Artist artist) throws ClassNotFoundException, SQLException {
		
		databaseConnection db = new databaseConnection();
		
		Connection conn = db.connect();
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		
		String sql = "UPDATE Artist SET Name=? WHERE ArtistId=?";
		
		statement = conn.prepareStatement(sql);
		
		statement.setString(1, artist.getName());
		statement.setLong(2, artist.getId());
		
		statement.execute();
				
		db.close(rs, statement, conn);			
	}

	public void deleteArtist(String artistId) throws ClassNotFoundException, SQLException {
		
		databaseConnection db = new databaseConnection();
		
		Connection conn = db.connect();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			long theArtistId = Long.parseLong(artistId);
			
			String sql = "Delete FROM Artist WHERE ArtistId=?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, theArtistId);
			
			stmt.execute();
			
		} finally {
			
			db.close(rs, stmt, conn);
			
		}
	}
}
