package com.musicstore.web.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ArtistPageServlet")
public class ArtistPageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArtistDao artistDao = new ArtistDao();
	
//	 POISTA TÄMÄ MIKÄLI ALBUMSERVLET TOIMII
	 private albumDao albumsDao = new albumDao();
	
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			artistDao = new ArtistDao();
			
//			 POISTA TÄMÄ MIKÄLI ALBUMSERVLET TOIMII
			 albumsDao = new albumDao();
		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String theCommand = request.getParameter("command");
			
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			switch (theCommand) {
			
			case "LIST":
				listArtists(request, response);
				break;
			
			case "AddArtist":
				addArtist(request, response);
				break;
				
			case "SearchArtist":
				searchArtistByName(request, response);
				break;
				
			case "loadArtistId":
				getArtist(request, response);
				break;
				
			case "changeArtistName":
				changeArtistName(request, response);
				break;
				
			case "deleteArtist":
				deleteArtist(request, response);
				break;
				
			// POISTA TÄMÄ MIKÄLI ALBUMSERVLET TOIMII
				
			case "listAlbumsByArtist":
				listAlbumsByArtist(request, response);
				break;
				
			default:
				listArtists(request, response);
				break;
				
			}
						
		} catch (Exception exc) {

			throw new ServletException(exc);
		}
		
	}

//	 POISTA TÄMÄ MIKÄLI ALBUMSERVLET TOIMII
	
	private void listAlbumsByArtist(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException, IOException {
		
		String artistId = request.getParameter("artistId");
		
		long longArtistId = Long.parseLong(artistId);

		Artist artist = artistDao.findArtistById(longArtistId);
		
		List<Album> albums = albumsDao.findAlbumsByArtist(artist);
				
		System.out.println(albums);
				
		request.setAttribute("ALBUM_LIST", albums);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/artist-page.jsp");
		
		dispatcher.forward(request, response);
		
	}

	private void deleteArtist(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException, IOException {
		
		String artistId = request.getParameter("artistId");
		
		artistDao.deleteArtist(artistId);
		
		listArtists(request, response);
		
	}

	private void changeArtistName(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
		
		String artistId = request.getParameter("artistId");
		
		long longArtistId = Long.parseLong(artistId);
		
		String artistName = request.getParameter("artistName");
		
		Artist artist = new Artist(longArtistId, artistName);
		
		artistDao.changeArtistName(artist);
		
		// TÄMÄ LÄHETTÄÄ TAKAISIN ARTISTISIVULLE!
		
		listArtists(request, response);
		
	}

	private void getArtist(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException, IOException {

		String theArtistId = request.getParameter("artistId");
		
		long longArtistId = Long.parseLong(theArtistId);
		
		Artist artist = artistDao.findArtistById(longArtistId);
		
		request.setAttribute("THE_ARTIST", artist);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Change-artistName.jsp");
		
		dispatcher.forward(request, response);
		
	}

	private void searchArtistByName(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException, IOException {
		
		String searchName = request.getParameter("artistName");
		
		List<Artist> artists = artistDao.findArtistByName(searchName);
		
		request.setAttribute("ARTIST_LIST", artists);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-artists.jsp");
		dispatcher.forward(request, response);
		
	}

	private void addArtist(HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException, ServletException, IOException {
		
		String artistName = request.getParameter("artistName");
		
		Artist newArtist = new Artist(artistName);
		
		artistDao.addArtist(newArtist);
		
		listArtists(request, response);
		
	}

	private void listArtists(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException, IOException {
		
		List<Artist> artists = artistDao.getAllArtists();
		
		request.setAttribute("ARTIST_LIST", artists);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-artists.jsp");
		
		dispatcher.forward(request, response);
		
	}
}
