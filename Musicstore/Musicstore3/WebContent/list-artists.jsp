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
				</tr>
							
			<c:forEach var="tempArtist" items="${ARTIST_LIST}">
			
				<tr>
					<td align="center">${tempArtist.id}</td>
					<td align="center">${tempArtist.name}</td>
				</tr>
				
			</c:forEach>
			
			</table>
			
		
		</div>
	
	</div>

</body>

</html>