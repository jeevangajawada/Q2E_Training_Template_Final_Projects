<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loan Application Form</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</head>
<body>

<!--
	write the html code to accept laon info from user and send to placeloan servlet
-->

<jsp:include page="header.jsp"/>
<hr/>

<H3> Loan Application Form </H3>

<form action="user?action=placeloan" method="post">
		
		<div>
			<div><label for="loanname">Loan Name</label>
			<input type="text" id="loanname" name="loanname"> </div> <br>
		</div>
		
		<div>
			<div><label for="loanamtrequested">Loan Amount</label>
			<input type="text" id="loanamtrequested" name="loanamtrequested"> </div> <br>
		</div>
		
		<div><label for="BusinessStructure">Business Structure</label>
		<select name="busstructure">
		<option value="busstrdefault" selected>Please Select</option>
		<option value="Individual">Individual</option>
		<option value="Organization">Organization</option>
		</select>
		</div> <br>
		
		<div><label for="BillingIndicator">Billing Indicator</label>
		<select name="billingindicator">
		<option value="billindidefault" selected>Please Select</option>
		<option value="Salaried">Salaried</option>
		<option value="Non Salaried">Non Salaried</option>
		</select>
		</div> <br>
		
		<div><label for="TaxIndicator">Tax Indicator</label>
		<select name="taxindicator">
		<option value="taxinddefault" selected>Please Select</option>
		<option value="Tax Payer">Tax Payer</option>
		<option value="Non Tax Payer">Non Tax Payer</option>
		</select>
		</div> <br>
		
		<div>
			<div><label for="address">Enter Address</label>
			<input type="text" id="address" name="address"> </div> <br>
		</div>
		
		<div>
			<div><label for="email">Enter Email</label>
			<input type="text" id="email" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"> </div> <br>
		</div>
		
		<div>
			<div><label for="mobile">Enter Mobile</label>
			<input type="text" id="mobile" name="mobile" pattern="[789][0-9]{9}"> </div> <br>
		</div>
		
		<div>
			<div><label for="appdate">Loan Application Date (YYYY-MM-DD) </label>
			<input type="text" id="appdate" name="appdate"> </div> <br>
		</div>
		
		<div>
			<div><input type="submit" value="Submit"> </div>
		</div>
</form>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>