<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

	<html>
	
		<head>
		
			<meta charset="UTF-8">
			<title>Edit An Existing List</title>
			
		</head>
		
		<body>
		
			<form action = "EditListDetailsServlet" method="post">
			
				<input type ="hidden" name = "id" value= "${listToEdit.id}">
				
				<p>
					List Name: <input type="text" name="listName" value="${listToEdit.listName}"><br />
				<p>
				
				<p>
					Trip date: 
					<input type="text" name="month" placeholder="mm" size="4" value="${month}">
					<input type ="text" name ="day" placeholder="dd" size="4" value= "${date}">, 
					<input type ="text" name="year" placeholder="yyyy" size="4" value= "${year}">
				</p>
				
				<p>
					Shopper Name: 
					<input type = "text" name="studioName" value="${listToEdit.studio.studioName}">
				<p>
					Available Equipment:
				</p>
				
				<select name="allItemsToAdd" multiple size="6">
					<c:forEach items="${requestScope.allItems}" var="currentitem">
 						<option value = "${currentitem.id}">${currentitem.brand} | ${currentitem.model}</option>
					</c:forEach>
				</select>
				<br /><br />
				<input type = "submit" value="Edit List and Add Items">
			</form>
			<br />
			<button onclick="window.location.href='ViewAllListsServlet';">Cancel:</button><br /><br /><br />
			<button onclick="window.location.href='index.html';">Add Equipment:</button><br /><br />
	</body>
</html>
