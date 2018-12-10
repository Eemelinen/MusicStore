<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>My Music Store</title>
</head>
<body>

	<div>
		<h2>My Music Store</h2>
	</div>
	<div>
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
					<td align="center"><a href="${albumPageLink}"><input type="submit" value=" Albums "></td>
				</tr>
				
				
			</c:forEach>
			
			</table>
		</div>
	</div>
	<p><a href="ArtistPageServlet">Back to List</a>
	
</body>
</html>