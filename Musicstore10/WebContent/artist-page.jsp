<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
	<title>Artist Page</title>
</head>

<body>
	
	<div>
		<div>
		
			<h2>Albums:</h2>
		
			<table border="1">
			
				<tr>
					<th>ID</th>
					<th>Album</th>
				</tr>
				
				<c:forEach var="tempAlbum" items="${ALBUM_LIST}">
				
					<c:url var="trackPageLink" value="TrackPageServlet">	
						<c:param name="command" value="listTracksByAlbum"/>
						<c:param name="albumId" value="${tempAlbum.albumId}"/>
					</c:url>
								
					<tr>
						<td align="center"> ${tempAlbum.albumId} </td>
						<td align="center"> <a href="${trackPageLink}">${tempAlbum.albumTitle}</a> </td>
					</tr>
				
				</c:forEach>

			</table>
						
		</div>
	</div>
	
	<p><a href="ArtistPageServlet">Back to List</a>

</body>
</html>