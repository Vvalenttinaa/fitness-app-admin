<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="beans.AdvicerBean" %>
 <%@ page import="dto.Advicer" %>
 <jsp:useBean id="advicerBean" type="beans.AdvicerBean" scope="session"/>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Edit advicer</title>
    <link rel="stylesheet" href="styles/add-user.css">

</head>
<body>
<%@include file="header.jsp"%>
<form action="?action=editAdvicer&id=<%=advicerBean.getAdvicer().getId()%>" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" value="${advicerBean.getAdvicer().getUsername()}" required><br><br>

    <label for="firstName">First Name:</label>
    <input type="text" id="firstName" name="firstName" value="${advicerBean.getAdvicer().getFirstName()}" required><br><br>

    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName" value="${advicerBean.getAdvicer().getLastName()}" required><br><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" value="${advicerBean.getAdvicer().getPassword()}" required><br><br>

    <input type="submit" name="submit" value="Submit">
</form>

</body>
</html>