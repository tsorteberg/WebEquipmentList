<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Create a new list</title>
	</head>
	
	<body>
		<h1>Create List:</h1>
		<form action = "CreateNewListServlet" method="post">
		
			<p>
				List Name: <input type ="text" name = "listName"><br />
			</p>
			
			<p>
				Create Date: 
				<input type ="text" name = "month" placeholder="mm" size="4"> 
				<input type ="text" name = "day" placeholder="dd" size="4">, 
				<input type ="text" name = "year" placeholder="yyyy" size="4">
			</p>
			
			<p>
				Studio Name: <input type = "text" name = "studioName"><br />
			</p>
			
			<p>
				Available Equipment:
			</p>
			
			<select name="allItemsToAdd" multiple size="6">
				<c:forEach items="${requestScope.allItems}" var="currentitem">
 					<option value = "${currentitem.id}">${currentitem.brand} | ${currentitem.model}</option>
				</c:forEach>
			</select>
			<br /><br />
			<input type = "submit" value="Create List and Add Items:">
		</form>
		<br />
		<button onclick="window.location.href='ViewAllListsServlet';">Cancel:</button><br /><br /><br />
		<button onclick="window.location.href='index.html';">Add Equipment:</button><br /><br />
	</body>
</html>