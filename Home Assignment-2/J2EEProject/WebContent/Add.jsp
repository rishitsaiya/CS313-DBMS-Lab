<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Form - Book Addition</title>
</head>
<body>
<h2>Deatils of Book:</h2>
<form action="AddServlet" method="post">
			<table style="with: 50%">
				<tr>
					<td>Book ID: </td>
					<td><input type="text" name="id" /></td>
				</tr>
				<tr>
					<td>Title: </td>
					<td><input type="text" name="title" /></td>
				</tr>
				<tr>
					<td>Author: </td>
					<td><input type="text" name="author" /></td>
				</tr>
				<tr>
					<td>Category: </td>
					<td><input type="text" name="cat" /></td>
				</tr>
			</table>
			<input type="submit" value="Submit" /></form>
</body>
</html>
