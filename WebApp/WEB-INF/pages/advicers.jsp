<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dto.Advicer" %>

    
<jsp:useBean id="advicerBean" class="beans.AdvicerBean" scope="session"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Advicers</title>
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
                        <h2>Advicers</h2>
                    </div>
                    </div>
                </div>
            </div>
            <button class="btn" onclick="location.href='?action=addAdvicer'">Add New Advicer</button>
            
            <table id="myTable" class="table table-striped">
                <thead>
                <tr>
                    <th style="width: fit-content;">Id</th>
                    <th style="width: fit-content;">Username</th>
                    <th style="width: fit-content;">First Name</th>
                    <th style="width: fit-content;">Last Name</th>
                    <th style="width: fit-content;">Password</th>
                    <th style="width: fit-content;">Actions</th>
                </tr>
                </thead>
                <tbody>
                <% for(Advicer advicer:advicerBean.getAllAdvicers()) {%>
                <tr>
					<td><span><%=advicer.getId()%></span></td>
					<td><span><%=advicer.getUsername()%></span></td>
					<td><span><%=advicer.getFirstName()%></span></td>
					<td><span><%=advicer.getLastName()%></span></td>
					<td><span><%=advicer.getPassword()%></span></td>
					<td>
            			<button class="btn" onclick="location.href='?action=editAdvicer&id=<%=advicer.getId()%>'">Edit</button>
            			<button class="btn" onclick="location.href='?action=deleteAdvicer&id=<%=advicer.getId()%>'">Delete</button>
            
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