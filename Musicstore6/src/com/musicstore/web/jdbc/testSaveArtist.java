package com.musicstore.web.jdbc;

import java.sql.SQLException;

public class testSaveArtist {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		ArtistDao dao = new ArtistDao();
		
		Artist artist = new Artist("AABB");
		
		dao.addArtist(artist);

		System.out.println(dao.getAllArtists());

	}

}
