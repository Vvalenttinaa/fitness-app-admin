<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Admin Application</title>
    <link rel="stylesheet" href="styles/home.css">

</head>
<body>
<%@include file="header.jsp"%>
<br>
<div class="container">
    <div class="menu">
        <h2>Actions</h2>
        <nav>
            <ul>
                <li><a href="?action=logout">Logout</a></li>
                <li><a href="?action=categories">Categories</a></li>
                <li><a href="?action=users">Users</a></li>
                <li><a href="?action=advicers">Advicers</a></li>
                <li><a href="?action=logs">Logs</a></li>
            </ul>
        </nav>
    </div>
</div>

</body>
</html>