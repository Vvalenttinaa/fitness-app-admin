<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="beans.AttributeBean" %>
 <%@ page import="dto.Attribute" %>
 <jsp:useBean id="attributeBean" type="beans.AttributeBean" scope="session"/>
 <jsp:useBean id="categoryBean" type="beans.CategoryBean" scope="session"/>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Add attribute</title>
    <link rel="stylesheet" href="styles/add-user.css">

</head>
<body>
<%@include file="header.jsp"%>
<form action="?action=addAttribute&id=<%=categoryBean.getCategory().getId()%>" method="post">
    <label for="username">Name:</label>
    <input type="text" id="name" name="name" required><br><br>
    <input type="submit" name="submit" value="Submit">
</form>

</body>

</html>