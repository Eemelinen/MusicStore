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
	
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			artistDao = new ArtistDao();
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
				
			default:
				listArtists(request, response);
				break;
				
			}
						
		} catch (Exception exc) {

			throw new ServletException(exc);
		}
		
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
