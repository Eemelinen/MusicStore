package com.musicstore.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDao {

public List<Track> findTracksByAlbum(Album album) throws ClassNotFoundException, SQLException {
		
		databaseConnection db = new databaseConnection();
		
		Connection conn = db.connect();
		PreparedStatement statement = null;
		ResultSet results = null;
		
		List<Track> trackList = new ArrayList<>();
		
		try {
			
			statement = conn.prepareStatement("SELECT * FROM Track WHERE AlbumId = (SELECT AlbumId FROM Album WHERE Title = ?)");
			
			statement.setString(1, album.getAlbumTitle());
			
			results = statement.executeQuery();
		
			while (results.next()) {
				
				long trackId = results.getLong("TrackId");
				String trackName = results.getString("Name");
				long albumId = results.getLong("AlbumId");
				trackList.add(new Track(trackId, trackName, albumId));	
			}
			
		} catch(SQLException e) {
			
			throw new RuntimeException(e);
			
		} finally {
			
			db.close(results, statement, conn);
		}
		
		return trackList;					
	}	
}
