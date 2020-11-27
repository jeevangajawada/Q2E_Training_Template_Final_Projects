<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New User Registration</title>
</head>
<body>
	<!-- read user name and password from user to create account
	     and send to usercontrollers registeruser method
	-->

<jsp:include page="header.jsp"/>
<hr/>

<H3> User Registration Page </H3>

	<form action="user?action=registernewuser" method="post">
		<div>
			<div><label for="Nusername">Enter User Name</label>
			<input type="text" id="Nusername" name="Nusername"> </div> <br>
			<div><label for="Npassword">Enter Password</label>
			<input type="password" id="Npassword" name="Npassword"> </div> <br>
			<div><label for="regemail">Enter Email ID</label>
			<input type="text" id="regemail" name="regemail"  pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"> </div>
		</div> <br>
		
		<div>
			<div><label for="regContactnum">Enter Mobile no</label>
			<input type="text" id="regContactnum" name="regContactnum" pattern="[789][0-9]{9}"> </div>
		</div> <br>
		
		<div>
		<div><input type="submit" value="Submit"> </div>
		</div>
		</form>
<hr/>	
	<jsp:include page="footer.jsp"/>
	
</body>
</html>