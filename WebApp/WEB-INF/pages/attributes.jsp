<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dto.Attribute" %>
<jsp:useBean id="attributeBean" class="beans.AttributeBean" scope="session"/>
<jsp:useBean id="categoryBean" class="beans.CategoryBean" scope="session"/>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Attributes</title>
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
                        <h2>Attributes for <%=categoryBean.getCategory().getName()%> </h2>
                    </div>
                    </div>
                </div>
            </div>
            <button class="btn" onclick="location.href='?action=addAttribute&id=<%=categoryBean.getCategory().getId()%>'">Add Attribute</button>
            
            <table id="myTable" class="table table-striped">
                <thead>
                <tr>
                    <th style="width: fit-content;">Id</th>
                    <th style="width: fit-content;">Name</th>
                    <th style="width: fit-content;">Actions</th>
                </tr>
                </thead>
                <tbody>
                <% for(Attribute attribute: categoryBean.getAttributesByCategory()) {%>
                <tr>
					<td><span><%=attribute.getId()%></span></td>
					<td><span><%=attribute.getName()%></span></td>
					<td>
            			<button class="btn" onclick="location.href='?action=editAttribute&id=<%=attribute.getId()%>'">Edit</button>
            			<button class="btn" onclick="location.href='?action=deleteAttribute&id=<%=attribute.getId()%>'">Delete</button>
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