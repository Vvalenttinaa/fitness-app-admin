<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="beans.UserBean" %>
 <%@ page import="dto.User" %>
 <jsp:useBean id="userBean" type="beans.UserBean" scope="session"/>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Edit user</title>
    <link rel="stylesheet" href="styles/add-user.css">

</head>
<body>
<%@include file="header.jsp"%>
<form action="?action=editUser&id=<%=userBean.getUser().getId()%>" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" value="${userBean.getUser().getUsername()}" required><br><br>

    <label for="firstName">First Name:</label>
    <input type="text" id="firstName" name="firstName" value="${userBean.getUser().getFirstName()}" required><br><br>

    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName" value="${userBean.getUser().getLastName()}" required><br><br>

    <label for="email">E-mail:</label>
    <input type="email" id="mail" name="mail" value="${userBean.getUser().getMail()}" required><br><br>

    <label for="card">Card:</label>
    <input type="text" id="card" name="card" value="${userBean.getUser().getCard()}" required><br><br>

    <label for="active">Active:</label>
    <input type="text" id="active" name="active" value="${userBean.getUser().getActive()}" required><br><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" value="${userBean.getUser().getPassword()}" required><br><br>

	<label for="city">City:</label>
    <input type="text" id="city" name="city" value="${userBean.getUser().getCity()}" required><br><br>

    <input type="submit" name="submit" value="Submit">
</form>

</body>
</html>