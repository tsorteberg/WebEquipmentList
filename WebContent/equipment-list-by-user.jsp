<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>

	<head>
	
		<meta charset="UTF-8">
		<title>Equipment Lists:</title>
		
	</head>
	
	<body>
		<form method = "post" action = "ListNavigationServlet">
		
			<table>
			
				<c:forEach items="${requestScope.allLists}" var="currentlist">
				
					<tr>
 						<td><input type="radio" name="id" value="${currentlist.id}"></td>
 						<td><h2>${currentlist.listName}</h2></td>
 					</tr>
 					
 					<tr>
 						<td colspan="3">Create Date: ${currentlist.createDate}</td>
 					</tr>
 					
 					<tr>
 						<td colspan="3">Studio: ${currentlist.studio.studioName}</td>
 					</tr>
 					
	 				<c:forEach var = "listVal" items = "${currentlist.listOfDevices}">
	 				
	 					<tr>
	 						<td></td>
	 						<td colspan="3">${listVal.brand}, ${listVal.model}</td>
	 					</tr>
	 					
	 				</c:forEach>
	 				
				</c:forEach>
				
			</table>
			
			<input type = "submit" value = "edit" name="doThisToList">
			<input type = "submit" value = "delete" name="doThisToList">
			<input type="submit" value = "add" name="doThisToList">
			
		</form>
		<br />
		<button onclick="window.location.href='index.html';">Add Equipment:</button><br /><br />
	</body>
</html>