<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dto.Category" %>

    
<jsp:useBean id="categoryBean" class="beans.CategoryBean" scope="session"/>

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
                        <h2>Categories</h2>
                    </div>
                    </div>
                </div>
            </div>
            <button class="btn" onclick="location.href='?action=addCategory'">Add New Category</button>
            
            <table id="myTable" class="table table-striped">
                <thead>
                <tr>
                    <th style="width: fit-content;">Id</th>
                    <th style="width: fit-content;">Name</th>
                    <th style="width: fit-content;">Actions</th>
                </tr>
                </thead>
                <tbody>
                <% for(Category category:categoryBean.getAllCategories()) {%>
                <tr>
					<td><span><%=category.getId()%></span></td>
					<td><span><%=category.getName()%></span></td>
					<td>
					    <button class="btn" onclick="location.href='?action=openAttributes&id=<%=category.getId()%>'">Open attributes</button>
            			<button class="btn" onclick="location.href='?action=editCategory&id=<%=category.getId()%>'">Edit</button>
            			<button class="btn" onclick="location.href='?action=deleteCategory&id=<%=category.getId()%>'">Delete</button>
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