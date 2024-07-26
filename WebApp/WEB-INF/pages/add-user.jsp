<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Add user</title>
    <link rel="stylesheet" href="styles/add-user.css">

</head>
<body>
<%@include file="header.jsp"%>
<form action="?action=addUser" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="firstName">First Name:</label>
    <input type="text" id="firstName" name="firstName" required><br><br>

    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName" required><br><br>

    <label for="email">E-mail:</label>
    <input type="email" id="mail" name="mail" required><br><br>

    <label for="card">Card:</label>
    <input type="text" id="card" name="card" required><br><br>

    <label for="active">Active:</label>
    <input type="text" id="active" name="active" required><br><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>

	<label for="city">City:</label>
    <input type="text" id="city" name="city" required><br><br>

    <input type="submit" name="submit" value="Submit">
</form>

</body>
</html>