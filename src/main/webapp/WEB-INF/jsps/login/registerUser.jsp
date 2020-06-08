<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Registration</title>
</head>
<body>

<h2>User Registration Page</h2>
<form action="registerUser" method="post">

<pre>
FirstName:<input type="text" name="firstname"/>
LastName:<input type="text" name="lastname"/>
UserName:<input type="text" name="email"/>
Password:<input type="password" name="password"/>
Confirm Password:<input type="password" name="confirm password"/>
<input type="submit" value="register"/>

</pre>
</form>
</body>
</html>