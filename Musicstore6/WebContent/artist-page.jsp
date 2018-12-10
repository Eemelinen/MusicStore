<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
	<title>Artist Page</title>
</head>

<body>
	
	<div>
		<div>
		
			<h1>${THE_ARTIST.name}</h1>
			
			<form action="ArtistPageServlet" method="GET">
			
				<input type="hidden" name="command" value="changeArtistName"/>
				
				<input type="hidden" name="artistId" value="${THE_ARTIST.id}"/>
									
				<p>Change artist name:</p>
				
				<input type="text" name="artistName" value="${THE_ARTIST.name}" />
				
				<input type="submit" value="Save">
				
				<c:url var="deleteLink" value="ArtistPageServlet">	
					<c:param name="command" value="deleteArtist"/>
					<c:param name="artistId" value="${THE_ARTIST.id}"/>
				</c:url>							
				
			</form>
			
			<form action="AlbumListServlet" method="GET">
				<input type="hidden" name="command" value="listAlbumsByArtist"/>
				<input type="hidden" name="artistId" value="${THE_ARTIST.id}"/>
			</form>
		
			<table border="1">
			
				<tr>
					<th>ID</th>
					<th>Album</th>
				</tr>
				
				<c:forEach var="tempAlbum" items="${ALBUM_LIST}">
				
					<tr>
						<td align="center">${tempAlbum.albumId}</td>
						<td align="center">${tempAlbum.albumName}</td>
					</tr>
				
				</c:forEach>

			</table>
							
			<p>
			<a href="${deleteLink}"
			onclick="if (!(confirm('Are you sure you want to delete this artist?'))) return false">
			Delete artist</a>
			</p>
			
			
		</div>
	</div>
	
	<p><a href="ArtistPageServlet">Back to List</a>

</body>
</html>