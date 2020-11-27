<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Process the Loan</title>
</head>
<body>
	<!-- write the code to read application number, and send it to admincontrollers
	     callemi method to calculate the emi and other details also provide links
	     to logout and admin home page
	-->

<jsp:include page="header.jsp"/>
<div align="right"><a href="adminhome1.jsp">Admin Home</a></div>
<hr/>

<H3> Approve the Loan </H3> <br>

	<form action="admin?action=callemi" method="post">
		<div>
			<div><label for="eLoanNumber">Enter Loan Number</label>
			<input type="text" id="eLoanNumber" name="eLoanNumber"> </div> <br>
		</div> <br>
		
		<div>
		<div><input type="submit" value="Approve Loan"> </div>
		</div>
	</form>

<hr/>
<div align="right"><a href="index.jsp">Logout</a></div>
	<jsp:include page="footer.jsp"/>
	
</body>

</body>
</html>