package com.musicstore.web.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlbumDaoTests {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		albumDao dao = new albumDao();
		
		ArtistDao artistDao = new ArtistDao();
		
//		// Test findAlbumById:
//		
//		System.out.println(dao.findAlbumById(1));
//		
//		// Test findAlbumsByArtist(Artist artist):
		
//		Artist artist = new Artist (1, "AC/DC");
//		
//		System.out.println(dao.findAlbumsByArtist(artist));
		
		long longArtistId = 3;
		
		Artist artist = artistDao.findArtistById(longArtistId);
		
		System.out.println(dao.findAlbumsByArtist(artist));

		
//		// Test findAlbumsByTitle:
//		
//		System.out.println(dao.findAlbumByTitle("For Those About To Rock We Salute You"));

	}

}
