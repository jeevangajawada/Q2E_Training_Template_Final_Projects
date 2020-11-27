<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Track Loan</title>
</head>
<body>
	<!-- write html code to read the application number and send to usercontrollers'
             displaystatus method for displaying the information
	-->

<jsp:include page="header.jsp"/>
<hr/>

<H3> Track your Loan Status </H3> <br>

	<form action="user?action=displaystatus" method="post">
		<div>
			<div><label for="eLoanNumber">Enter Loan Number</label>
			<input type="text" id="eLoanNumber" name="eLoanNumber"> </div> <br>
		</div> <br>
		
		<div>
		<div><input type="submit" value="Track Loan"> </div>
		</div>
		</form>
<hr/>	
	<jsp:include page="footer.jsp"/>
	
</body>
</html>