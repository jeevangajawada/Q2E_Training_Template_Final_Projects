<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Loan Information</title>
</head>
<body>

<jsp:include page="header.jsp"/>
<hr/>

<H3> Edit your Loan Information </H3> <br>

	<form action="user?action=editLoanProcess" method="post">
		
		<div>
			<div><label for="loanAccount">Loan Account</label>
			<input type="text" id="loanAccount" name="loanAccount" value ="${loanDetails.applno}"> </div> <br>
		</div>
		
		<div>
			<div><label for="loanname">Loan Name</label>
			<input type="text" id="loanname" name="loanname" value ="${loanDetails.purpose}"> </div> <br>
		</div>
		
		<div>
			<div><label for="loanamtrequested">Loan Amount</label>
			<input type="text" id="loanamtrequested" name="loanamtrequested" value ="${loanDetails.amtrequest}"> </div> <br>
		</div>
		
		<div><label for="BusinessStructure">Business Structure</label>
		<select name="busstructure">
		<option value ="${loanDetails.bstructure}" selected>Please Select</option>
		<option value="Individual">Individual</option>
		<option value="Organization">Organization</option>
		</select>
		</div> <br>
		
		<div><label for="BillingIndicator">Billing Indicator</label>
		<select name="billingindicator">
		<option value ="${loanDetails.bindicator}" selected>Please Select</option>
		<option value="Salaried">Salaried</option>
		<option value="Non Salaried">Non Salaried</option>
		</select>
		</div> <br>
		
		<div><label for="TaxIndicator">Tax Indicator</label>
		<select name="taxindicator">
		<option value ="${loanDetails.tindicator}" selected>Please Select</option>
		<option value="Tax Payer">Tax Payer</option>
		<option value="Non Tax Payer">Non Tax Payer</option>
		</select>
		</div> <br>
		
		<div>
			<div><label for="address">Enter Address</label>
			<input type="text" id="address" name="address" value ="${loanDetails.address}"> </div> <br>
		</div>
		
		<div>
			<div><label for="email">Enter Email</label>
			<input type="text" id="email" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" value ="${loanDetails.email}"> </div> <br>
		</div>
		
		<div>
			<div><label for="mobile">Enter Mobile</label>
			<input type="text" id="mobile" name="mobile" value ="${loanDetails.mobile}"> </div> <br>
		</div>
		
		<div>
			<div><label for="appdate">Loan Application Date (YYYY-MM-DD) </label>
			<input type="text" id="appdate" name="appdate" value ="${loanDetails.doa}"> </div> <br>
		</div>
		
		<div>
			<div><input type="submit" value="Update Details"> </div>
		</div>
</form>
<hr/>	
	<jsp:include page="footer.jsp"/>
	
</body>
</html>