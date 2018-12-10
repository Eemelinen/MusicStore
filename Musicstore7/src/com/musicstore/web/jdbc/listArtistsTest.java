package com.musicstore.web.jdbc;

import java.sql.SQLException;

public class listArtistsTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		ArtistDao dao = new ArtistDao();
		
		// Test findAllArtists:
		
		 System.out.println(dao.getAllArtists());
		
		
		// Test findArtistByName:
		
		 System.out.println(dao.findArtistByName("Accept"));
		
		
		//Test addArtist:
		
		 Artist artist = new Artist("AABB");
		
		 dao.addArtist(artist);

		 System.out.println(dao.getAllArtists());
		
		
		// Test findArtistById:
		
		System.out.println(dao.findArtistById(1));
		
	}
}


