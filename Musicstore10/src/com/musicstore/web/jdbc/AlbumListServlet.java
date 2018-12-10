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

/**
 * Servlet implementation class AlbumListServlet
 */
@WebServlet("/AlbumListServlet")
public class AlbumListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private albumDao albumsDao = new albumDao();
	private ArtistDao artistDao = new ArtistDao();


	@Override
	public void init() throws ServletException {
		super.init();
		try {
			albumsDao = new albumDao();
			artistDao = new ArtistDao();
		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			
			listAlbumsByArtist(request, response);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void listAlbumsByArtist(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException, IOException {
				
		String artistId = request.getParameter("artistId");
		
		long longArtistId = Long.parseLong(artistId);

		Artist artist = artistDao.findArtistById(longArtistId);
		
		List<Album> albums = albumsDao.findAlbumsByArtist(artist);
						
		request.setAttribute("ALBUM_LIST", albums);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/artist-page.jsp");
		
		dispatcher.forward(request, response);	
	}
}
