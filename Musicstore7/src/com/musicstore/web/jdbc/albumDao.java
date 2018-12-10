package com.musicstore.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class albumDao {
	
	public List<Album> findAlbumById(long id) throws ClassNotFoundException, SQLException {
		
		databaseConnection db = new databaseConnection();
		
		Connection conn = db.connect();
		PreparedStatement statement = null;
		ResultSet results = null;
		
		List<Album> albumList = new ArrayList<>();
		
		try {
			
			statement = conn.prepareStatement("SELECT * FROM Album WHERE AlbumId = ?");
			
			statement.setLong(1, id);
			
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
	
	// TÄTÄ EI VIELÄ TESTATTU. LOGIIKKA PITÄISI OLLA KUNNOSSA.
	
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
}
