package com.musicstore.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class albumDao {
	
	public Album findAlbumById(long id) throws ClassNotFoundException, SQLException {
		
		databaseConnection db = new databaseConnection();
		
		Connection conn = db.connect();
		PreparedStatement statement = null;
		ResultSet results = null;
				
		try {
			
			statement = conn.prepareStatement("SELECT * FROM Album WHERE AlbumId = ?");
			
			statement.setLong(1, id);
			
			results = statement.executeQuery();
						
			long albumId = results.getLong("AlbumId");
			String albumName = results.getString("Title");
			long artistId = results.getLong("ArtistId");
			
			Album album = new Album(albumId, albumName, artistId);
			
			return album;				
			
		} catch(SQLException e) {
			
			throw new RuntimeException(e);
			
		} finally {
			
			db.close(results, statement, conn);
		}					
	}
	
	public List<Album> findAlbumByTitle(String title) throws ClassNotFoundException, SQLException {
			
			databaseConnection db = new databaseConnection();
			
			Connection conn = db.connect();
			PreparedStatement statement = null;
			ResultSet results = null;
			
			List<Album> albumList = new ArrayList<>();
			
			try {
				
				statement = conn.prepareStatement("SELECT * FROM Album WHERE Title = ?");
				
				statement.setString(1, title);
				
				results = statement.executeQuery();
			
				while (results.next()) {
					
					long albumId = results.getLong("AlbumId");
					String albumName = results.getString("Title");
					long artistId = results.getLong("ArtistId");
					albumList.add(new Album(albumId, albumName, artistId));
					
				}
				
			} catch(SQLException e) {
				
				throw new RuntimeException(e);
				
			} finally {
				
				db.close(results, statement, conn);
			}
			
			return albumList;			
					
		}
		
	public List<Album> findAlbumsByArtist(Artist artist) throws ClassNotFoundException, SQLException {
		
		databaseConnection db = new databaseConnection();
		
		Connection conn = db.connect();
		PreparedStatement statement = null;
		ResultSet results = null;
		
		List<Album> albumList = new ArrayList<>();
		
		try {
			
			statement = conn.prepareStatement("SELECT * FROM Album WHERE ArtistId = (SELECT ArtistId FROM Artist WHERE Name = ?)");
			
			statement.setString(1, artist.getName());
			
			results = statement.executeQuery();
		
			while (results.next()) {
				
				long albumId = results.getLong("AlbumId");
				String albumName = results.getString("Title");
				long artistId = results.getLong("ArtistId");
				
				albumList.add(new Album(albumId, albumName, artistId));
				
			}
			
		} catch(SQLException e) {
			
			throw new RuntimeException(e);
			
		} finally {
			
			db.close(results, statement, conn);
		}
		
		return albumList;			
				
	}

	public void addAlbumToArtist(Album album) throws ClassNotFoundException, SQLException {
		
		databaseConnection db = new databaseConnection();
		
		Connection conn = db.connect();
		PreparedStatement statement = null;
		ResultSet generatedKeys = null;
		
		try {
			
			statement = conn.prepareStatement(
					"INSERT INTO album (Title, ArtistId)  Values (?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, album.getAlbumTitle());
			statement.setLong(2, album.getArtistId());

			statement.executeUpdate();
			
			generatedKeys = statement.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				
				long id = generatedKeys.getLong(1);
				album.setAlbumId(id);
			}
			
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
			
		} finally {
			
			db.close(generatedKeys, statement, conn);
			
		}		
	}
}
