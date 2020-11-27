<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display All Loans</title>
</head>

<body>
	<!-- write code to display all the loan details 
             which are received from the admin controllers' listall method
	--> 

<jsp:include page="header.jsp"/>
<hr/>

<H3> Loan Details </H3>
<form action="admin?action=listall" method="post">
		<c:choose>
		<c:when test="${loanInfoDetails == null || loanInfoDetails.isEmpty() }">
			<p> No Loans Found, Click here to apply <a href="userhome1.jsp"> <b> Apply for Loan </b> </a> <br>
		</c:when>
		<c:otherwise>
			<table border="1">
				<tr>
					<th>Loan Number</th>
					<th>Loan Purpose</th>
					<th>Loan Amount Requested</th>
					<th>Loan Status</th>
				</tr>
				<c:forEach items="${loanInfoDetails}" var="loanInfoDetails">
					<tr>
						<td>${loanInfoDetails.applno}</td>
						<td>${loanInfoDetails.purpose}</td>
						<td>${loanInfoDetails.amtrequest}</td>
						<td>${loanInfoDetails.status}</td>
						</tr>
				
				</c:forEach>
			</table>
		</c:otherwise>
		</c:choose>
</form>
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>