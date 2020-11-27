<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Calculate EMI Page</title>
</head>
<body>
<!-- write the html code to display hyperlinks for user functionalities -->

<jsp:include page="header.jsp"/>
<hr/>

<H3> Loan Approved and below are the details </H3>

<table border="1" >
	<tr>
		<td>Loan Number</td>
		<td>${loandbnumber}</td>
	</tr>
	<tr>
		<td>Loan Status</td>
		<td>${loandbstatus}</td>
	</tr>	
	<tr>
		<td>Loan Amount Approved</td>
		<td>${loandbamount}</td>
	</tr>	
	<tr>
		<td>Payment Start Date</td>
		<td>${paymentstartdate}</td>
	</tr>	
	<tr>
		<td>Loan Closure Date</td>
		<td>${paymentclosuredate}</td>
	</tr>	
	<tr>
		<td>Loan Term in Years</td>
		<td>${loanterinyears}</td>
	</tr>	
	<tr>
		<td>Monthly Payment</td>
		<td>${MonthlyPayment}</td>
	</tr>		
		</table> <br>
</body>

<hr/>	
	<jsp:include page="footer.jsp"/>
	
</html>