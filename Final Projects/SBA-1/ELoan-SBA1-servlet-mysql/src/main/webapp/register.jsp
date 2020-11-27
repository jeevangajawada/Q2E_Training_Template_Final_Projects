<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
<div align=center>
	<h2>eLoan Login</h2>
	<form action="registerNewUser" method="post">
		<div>
			<div><label for="regloginid">Enter login Id</label> </div>
			<div><input type="text" id="regloginid" name="regloginid"> </div>
			<div><label for="regpassword">Enter password</label> </div>
			<div><input type="text" id="regpassword" name="regpassword"> </div>
		</div>
		<div>
			<div><input type="submit" value="Login"> </div>
		</div>
	</form>
	</div>
<hr/>
<jsp:include page="footer.jsp"/>
</body>
</html>