package com.musicstore.web.jdbc;

import java.sql.SQLException;

public class TrackDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		TrackDao trackDao = new TrackDao();
		
		//Test findTracksByAlbum(Album album):
		
		Album album = new Album (1, "For Those About To Rock We Salute You", 1);
		
		System.out.println(trackDao.findTracksByAlbum(album));
		
//		 //Test findTracksByAlbum(String album):
//		 System.out.println(trackDao.findTracksByAlbum("For Those About To Rock We Salute You")); 
	}
}
