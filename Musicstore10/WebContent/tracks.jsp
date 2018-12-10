<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Album Page</title>
</head>
<body>

	<h2>Tracks:</h2>

	<table border="1">
	
		<tr>
			<th>ID</th>
			<th>Song</th>
		</tr>
		
		<c:forEach var="tempTrack" items="${TRACK_LIST}">
						
			<tr>
				<td align="center"> ${tempTrack.trackId} </td>
				<td align="center"> ${tempTrack.trackName} </td>
			</tr>
		
		</c:forEach>

	</table>
	
	<p><a href="ArtistPageServlet">Back to List</a>
	
</body>
</html>