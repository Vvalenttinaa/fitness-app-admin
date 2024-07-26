<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dto.User" %>

    
<jsp:useBean id="userBean" class="beans.UserBean" scope="session"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Users</title>
    <link rel="stylesheet" href="styles/users.css">
</head>
<body>
<%@include file="header.jsp"%>

	<div class="container-lg">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>Users</h2>
                    </div>
                    </div>
                </div>
            </div>
            <button class="btn" onclick="location.href='?action=addUser'">Add New User</button>
            
            <table id="myTable" class="table table-striped">
                <thead>
                <tr>
                    <th style="width: fit-content;">Id</th>
                    <th style="width: fit-content;">Username</th>
                    <th style="width: fit-content;">First Name</th>
                    <th style="width: fit-content;">Last Name</th>
                    <th style="width: fit-content;">E-mail</th>
                    <th style="width: fit-content;">Card</th>
                    <th style="width: fit-content;">Active</th>
                    <th style="width: fit-content;">Actions</th>
                </tr>
                </thead>
                <tbody>
                <% for(User user:userBean.getAllUsers()) {%>
                <tr>
					<td><span><%=user.getId()%></span></td>
					<td><span><%=user.getUsername()%></span></td>
					<td><span><%=user.getFirstName()%></span></td>
					<td><span><%=user.getLastName()%></span></td>
					<td><span><%=user.getMail()%></span></td>
					<td><span><%=user.getCard()%></span></td>
					<td><span><%=user.getActive()%></span></td>
					<td>
            			<button class="btn" onclick="location.href='?action=editUser&id=<%=user.getId()%>'">Edit</button>
            			<button class="btn" onclick="location.href='?action=deleteUser&id=<%=user.getId()%>'">Delete</button>
            
       				</td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>