<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Add Album</title>
</head>
<body>

	<div>
		<div>
			<h2>Add new Album</h2>
			
			<form action="ArtistPageServlet" method="GET">
			
				<input type="hidden" name="command" value="addAlbumToArtist"/>
				
				<input type="hidden" name="artistId" value="${THE_ARTIST.id}"/>				
									
				<p>Give new album here:</p>
				
				<input type="text" name="albumName" value="Enter name" />
				
				<input type="submit" value="Save">							
				
			</form>
			
		</div>
	</div>
	
	<p><a href="ArtistPageServlet">Back to List</a>

</body>
</html>