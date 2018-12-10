<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
	<title>Artist Page</title>
</head>

<body>
	
	<div>
		<div>
				
			<h1>${THE_ARTIST.name}</h1>

		
			<table border="1">
			
				<tr>
					<th>ID</th>
					<th>Album</th>
				</tr>
				
				<c:forEach var="tempAlbum" items="${ALBUM_LIST}">
				
					<tr>
						<td align="center"> ${tempAlbum.albumId} </td>
						<td align="center"> ${tempAlbum.albumTitle} </td>
					</tr>
				
				</c:forEach>

			</table>
			
			-->
			
			
		</div>
	</div>
	
	<p><a href="ArtistPageServlet">Back to List</a>

</body>
</html>