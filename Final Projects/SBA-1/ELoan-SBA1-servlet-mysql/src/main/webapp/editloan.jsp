<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Loan Application</title>
</head>
<body>
	<!-- read the application number to edit from user and send to 
	     user controller to edit info
	-->

<jsp:include page="header.jsp"/>
<hr/>

<H3> Edit your Loan Information </H3> <br>

	<form action="user?action=editloan" method="post">
		
		<div>
			<div><label for="loanAccount">Loan Account</label>
			<input type="text" id="loanAccount" name="loanAccount"> </div> <br>
		</div>
		<div>
			<div><input type="submit" value="Submit"> </div>
		</div>
</form>
<hr/>	
	<jsp:include page="footer.jsp"/>
	
</body>
</html>