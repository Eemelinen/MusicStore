package com.musicstore.web.jdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

public class artistDaoTest {
	
	@Test
	public void testArtistListIsNotEmpty() throws ClassNotFoundException, SQLException {
		
		ArtistDao dao = new ArtistDao();
		List<Artist> all = dao.getAllArtists();
		
		assertFalse(all.isEmpty());
	}
	
	@Test
	public void testTheNameOfTheFirstArtist() throws ClassNotFoundException, SQLException {
		
		ArtistDao dao = new ArtistDao();
		List<Artist> all = dao.getAllArtists();
		
		assertEquals("A Cor Do Som", all.get(0).getName());
	}

}
