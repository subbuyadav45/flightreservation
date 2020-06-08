<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FLIGHTS</title>
</head>
<body>
<h2>Flights:</h2>
<table border="1">
<tr>
<th>Airlines</th>
<th>DepartureCity</th>
<th>Arrival City</th>
<th>DepartureTime</th>
</tr>
<c:forEach items="${flights}" var="flight">
<tr>
<td>${flight.operatingAirLines} </td>
<td>${flight.departureCity}</td>
<td>${flight.arrivalCity}</td>
<td>${flight.estimatedDepartureTime}</td>
<td><a href="showCompleteReservation?flightId=${flight.id}">select</a></td>

</tr>


</c:forEach>
</table>
</body>
</html>