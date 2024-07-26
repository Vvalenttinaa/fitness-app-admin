<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="dto.Logger" %>

<jsp:useBean id="loggerBean" class="beans.LoggerBean" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logs</title>
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
                        <h2>Logs</h2>
                    </div>
                    </div>
                </div>
            </div>
            
            <table id="myTable" class="table table-striped">
                <thead>
                <tr>
                    <th style="width: fit-content;">Id</th>
                    <th style="width: fit-content;">Date</th>
                    <th style="width: fit-content;">Log</th>
                </tr>
                </thead>
                <tbody>
                <% for(Logger log:loggerBean.getAll()) {%>
                <tr>
					<td><span><%=log.getId()%></span></td>
					<td><span><%=log.getDate()%></span></td>
					<td><span><%=log.getLog()%></span></td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>