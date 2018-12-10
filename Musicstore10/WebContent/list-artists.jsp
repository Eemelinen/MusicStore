<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!--

This app has been done as a part of an Haaga-Helia Java-programming course.
The goal of the assignment was to practice web-development with Java using Chinook-database,
SQLlite Tomcat, servlets, JSP, JDBC and JSTL etc.

This app enables you to:
- browse artists, albums and songs in Chinook database (listed on different pages)
- search for artists by name (Search is not case sensitive. You can also just type part of the name)
- add artists
- add albums to artists
- change artist name
- delete artists

Test-classes used while developing artist, album and track -classes and DAOs are also included.
Github was used for version control and the project can be also be found at:
https://github.com/Eemelinen/Musicstore

-->

<html>
<head>
	<title>My Music Store</title>
</head>
<body>

	<div>
		<h2>My Music Store</h2>
	</div>
	
	<div>
			
		<form action="ArtistPageServlet" method="GET">
		
			<input type="hidden" name="command" value="SearchArtist"/>
			
			<p>Search Artist by name:</p>
			<input type="text" name="artistName"/>
			<input type="submit" value="Search" />
				
		</form>
		
		<input type="button" value="Add Artist"
			onclick="window.location.href='add-artist.jsp'; return false;" />
			
		<p></p>
	
		<table border="1">
		
			<tr>
				<th>ID</th>
				<th>Artist</th>
				<th>Albums</th>
			</tr>
							
			<c:forEach var="tempArtist" items="${ARTIST_LIST}">
						
				<c:url var="tempLink" value="ArtistPageServlet">	
					<c:param name="command" value="loadArtistId"/>
					<c:param name="artistId" value="${tempArtist.id}"/>
				</c:url>
				
				<c:url var="albumPageLink" value="AlbumListServlet">	
					<c:param name="command" value="listAlbumsByArtist"/>
					<c:param name="artistId" value="${tempArtist.id}"/>
				</c:url>
			
				<tr>
					<td align="center"><a href="${tempLink}">${tempArtist.id}</a></td>
					<td align="center"><a href="${tempLink}">${tempArtist.name}</a></td>
					<td align="center"><a href="${albumPageLink}"><input type="submit" value=" Albums "></a></td>
				</tr>	
				
			</c:forEach>
		
		</table>
		
	</div>
	<p><a href="ArtistPageServlet">Back to List</a>
	
</body>
</html>