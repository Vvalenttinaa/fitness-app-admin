<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="beans.CategoryBean" %>
 <%@ page import="dto.Category" %>
 <jsp:useBean id="categoryBean" type="beans.CategoryBean" scope="session"/>
  <jsp:useBean id="attributeBean" type="beans.AttributeBean" scope="session"/>
 

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Edit attribute</title>
    <link rel="stylesheet" href="styles/add-user.css">

</head>
<body>
<%@include file="header.jsp"%>
<form action="?action=editAttribute&id=<%=attributeBean.getAttribute().getId()%>" method="post">
    <label for="username">Name:</label>
    <input type="text" id="name" name="name" value="${attributeBean.getAttribute().getName()}" required>
    <br><br>
    <input type="submit" name="submit" value="Submit">
</form>

</body>
</html>