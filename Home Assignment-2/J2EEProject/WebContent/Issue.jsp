<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Form - Book Issue</title>
</head>
<body>
<h2>Issue Details </h2>
<form action="IssueServlet" method="post">
			<table style="with: 50%">
				<tr>
					<td>Student ID: </td>
					<td><input type="text" name="s_id" /></td>
				</tr>
				<tr>
					<td>Book ID: </td>
					<td><input type="text" name="b_id" /></td>
				</tr>
				<tr>
					<td>Issue Date: </td>
					<td><input type="text" name="date" /></td>
				</tr>

			</table>
			<input type="submit" value="Submit" /></form>
</body>
</html>
