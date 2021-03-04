<!--  
/**
 * @author Tom Sorteberg - tsorteberg
 * CIS175 - Spring 2021
 * Feb 25, 2021
 */
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Item</title>
</head>
<body>
	<h1>Studio Equipment List</h1>
	<h2>Edit Item:</h2>
	<form action="editItemServlet" method="post">
		<label for="brand">Brand:</label>
		<input type="text" name="brand" value="${itemToEdit.brand}">
		<label for="model">Model:</label>
		<input type="text" name="model" value="${itemToEdit.model}">
		<input type="hidden" name="id" value="${itemToEdit.id}">
		<input type = "submit" value="Save">	
	</form>
	<a href="ViewAllItemsServlet">Cancel</a>
</body>
</html>