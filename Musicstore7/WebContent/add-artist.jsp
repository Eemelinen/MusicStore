<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
	<title>New Artist</title>
</head>

<body>
	
	<div>
		<div>
			<h2>Add new Artist</h2>
			
			<form action="ArtistPageServlet" method="GET">
			
				<input type="hidden" name="command" value="AddArtist"/>
				
				<input type="hidden" name="artistId" value="${THE_ARTIST.id}"/>
				
				<p>Enter artist name here:</p>
				
				<input type="text" name="artistName" />
				
				<input type="submit" value="Save">
				
			</form>
			
		</div>
	</div>
	
	<p><a href="ArtistPageServlet">Back to List</a>

</body>
</html>