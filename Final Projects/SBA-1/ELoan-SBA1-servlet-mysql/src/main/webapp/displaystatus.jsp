<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display Status</title>
</head>

<body>
<!-- write the html code to display hyperlinks for user functionalities -->

<jsp:include page="header.jsp"/>
<hr/>

<H3> Loan Details </H3>

<table border="1" >
	<tr>
		<td>Loan Number</td>
		<td>${loandbnumber}</td>
	</tr>
	<tr>
		<td>Loan Status</td>
		<td>${loandbstatus}</td>
	</tr>		
		</table> <br>
</body>

<hr/>	
	<jsp:include page="footer.jsp"/>
	
</html>