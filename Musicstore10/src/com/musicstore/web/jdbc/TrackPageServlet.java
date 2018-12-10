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
 * Servlet implementation class TrackPageServlet
 */
@WebServlet("/TrackPageServlet")
public class TrackPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private albumDao albumsDao = new albumDao();
	private ArtistDao artistDao = new ArtistDao();
	private TrackDao trackDao = new TrackDao();


	@Override
	public void init() throws ServletException {
		super.init();
		try {
			albumsDao = new albumDao();
			artistDao = new ArtistDao();
			trackDao = new TrackDao();
		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			listTracksByAlbum(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}	
	}

	private void listTracksByAlbum(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		
		String albumId = request.getParameter("albumId");
		
		long longAlbumId = Long.parseLong(albumId);

		Album album = albumsDao.findAlbumById(longAlbumId);
		
		List<Track> tracks = trackDao.findTracksByAlbum(album);
		
		request.setAttribute("TRACK_LIST", tracks);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/tracks.jsp");
		
		dispatcher.forward(request, response);	
		
	}
}
