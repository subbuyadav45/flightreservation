<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Find Flights</title>
</head>
<body>
<h2>Find flights</h2>

<form action="findflights" method="post" >

FROM:<input type="text" name="from"/>
TO:<input type="text" name="to"/>
DEPARTURE DATE:<input type="text" name="departureDate"/>
<input type="submit" value="search"/>


</form>




</body>
</html>