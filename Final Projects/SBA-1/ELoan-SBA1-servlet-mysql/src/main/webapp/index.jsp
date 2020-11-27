<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>eLoan System</title>
</head>
<body>

	<!-- write the html code to read user credentials and send it to validateservlet
	    to validate and user servlet's registernewuser method if create new user
	    account is selected
	-->
	
<jsp:include page="header.jsp"/>
<hr/>
<div>
	<h1> e-Loan Home Page </h1>
	<H3> Admin Login </H3>
	<form action="admin?action=adminlogin" method="post">
		<div>
			<div><label for="loginuserid">Enter Admin User Id</label>
			<input type="text" id="loginuserid" name="loginuserid"> </div> <br>
		</div>
		<div>
			<label for="password">Enter Admin Password</label>
			<input type="text" id="password" name="password">
		</div> <br>
			<div> <input type="submit" value="Login">  </div> 
	</form>
</div>

<div>
	<H3> New User ? Register here </H3>
	<a href="user?action=register"><button> Register </button></a>
</div>
	<H3> Existing User ? Login here </H3>
	
<form action="user?action=validate" method="post">

			<div><label for="Nusername">Enter User Name</label>
			<input type="text" id="Nusername" name="Nusername"> </div> <br>
			<div><label for="Npassword">Enter Password</label>
			<input type="password" id="Npassword" name="Npassword"> </div> <br>
			
	<div><input type="submit" value="Login"> </div>
</form>

<hr/>
<jsp:include page="footer.jsp"/>

</body>
</html>