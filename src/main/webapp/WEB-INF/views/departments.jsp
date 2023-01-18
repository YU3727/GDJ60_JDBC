<%@page import="com.pooh.main.departments.DepartmentDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Departments List Page</h1>
	<%
		ArrayList<DepartmentDTO> ar = (ArrayList)request.getAttribute("list");
	%>
	<div>
		<h3><%= ar.get(0).getDepartment_name() %></h3>
		<h3><%= ar.get(1).getDepartment_name() %></h3>
		<h3><%= ar.get(2).getDepartment_name() %></h3>
		<h3><%= ar.get(3).getDepartment_name() %></h3>
		<h3><%= ar.get(4).getDepartment_name() %></h3>
	</div>

</body>
</html>