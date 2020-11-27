package com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.User;
import com.mysql.cj.xdevapi.Statement;


@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConnectionDao connDao;
	
	public void setConnDao(ConnectionDao connDao) {
		this.connDao = connDao;
	}
	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");
		System.out.println(jdbcURL + jdbcUsername + jdbcPassword);
		this.connDao = new ConnectionDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String viewName = "";
		try {
			switch (action) {
			case "registernewuser":
				viewName=registernewuser(request,response);
				break;
			case "validate":
				viewName=validate(request,response);
				break;
			case "placeloan":
				viewName=placeloan(request,response);
				break;
			case "application1":
				viewName=application1(request,response);
				break;
			case "editLoanProcess"  :
				viewName=editLoanProcess(request,response);
				break;
			case "registeruser":
				viewName=registerUser(request,response);
				break;
			case "register":
				viewName = register(request, response);
				break;
			case "application":
				viewName = application(request, response);
				break;
			case "trackloan":
				viewName = trackloan(request, response);
				break;
			case "editloan":
				viewName = editloan(request, response);
				break;	
			case  "displaystatus" :
				viewName=displaystatus(request,response);
				break;
			default : viewName = "notfound.jsp"; break;	
			}
		} catch (Exception ex) {
			
			throw new ServletException(ex.getMessage());
		}
			RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
			dispatch.forward(request, response);
	}
	private String validate(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		
		/* write the code to validate the user */
		String loginuserid= request.getParameter("Nusername");
		String loginpassword= request.getParameter("Npassword");
		
		List<User> userRegistrationDetails = connDao.getUserRegistrationDetails(loginuserid);
			
		int arrSize = userRegistrationDetails.size();
		
		String strDBUserName = "";
		String strDBPassword = "";
		String jspname = "";
		
		if (arrSize > 0){
			User objDBUserName = userRegistrationDetails.get(0);
			strDBUserName = objDBUserName.getUsername();
			strDBPassword = objDBUserName.getPassword();}
		else {	
			strDBUserName = "";
			strDBPassword = "";}
		
		if(loginuserid.equalsIgnoreCase(strDBUserName) && loginpassword.equalsIgnoreCase(strDBPassword)) {
			jspname= "userhome1.jsp";	}
		else {
			jspname="newuserui.jsp";}
		return jspname;
	}
	
	private String placeloan(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		/* write the code to place the loan information */

		String loanname= request.getParameter("loanname");
		int loanamtrequested= Integer.parseInt(request.getParameter("loanamtrequested"));
		String appdate= request.getParameter("appdate");
		String busstructure= request.getParameter("busstructure");
		String billingindicator= request.getParameter("billingindicator");
		String taxindicator= request.getParameter("taxindicator");
		String address= request.getParameter("address");
		String email= request.getParameter("email");
		String mobile= request.getParameter("mobile");
		String status= "Submitted";
		
		//Enter the new loan details to the Database
		connDao.addNewLoan(loanname, loanamtrequested, appdate, busstructure,
				billingindicator, taxindicator, address, email, mobile, status);
		
		//Code to get the new loan details
		List<LoanInfo> userLoanDetails = connDao.getLoanDetails(0, mobile);
		
		int arrSize = userLoanDetails.size();
		
		int loanNumber = 0;
		
		if (arrSize > 0){
			LoanInfo objLoanNumber = userLoanDetails.get(0);
			loanNumber = objLoanNumber.getApplno();
			}
		else {
			loanNumber = 0;
			}
		
		System.out.println("Loan Number -> " + loanNumber);
					
		//Set the details back to the JSP Page
		request.setAttribute("loandregnumber", "New Loan Registered with Number -> " + loanNumber);
				
		return "userhome1.jsp";
	}
	
	private String application1(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write the code to display the loan application page */
		
		return null;
	}
	private String editLoanProcess(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code to edit the loan info */
		
		LoanInfo updateLoanInfo= new LoanInfo();
		
		int loanAccount= Integer.parseInt(request.getParameter("loanAccount"));
		updateLoanInfo.setApplno(Integer.parseInt(request.getParameter("loanAccount")));
		updateLoanInfo.setPurpose(request.getParameter("loanname"));
		updateLoanInfo.setAmtrequest(Integer.parseInt(request.getParameter("loanamtrequested")));
		updateLoanInfo.setDoa(request.getParameter("appdate"));
		updateLoanInfo.setBstructure(request.getParameter("busstructure"));
		updateLoanInfo.setBindicator(request.getParameter("billingindicator"));
		updateLoanInfo.setTindicator(request.getParameter("taxindicator"));
		
		updateLoanInfo.setAddress(request.getParameter("address"));
		updateLoanInfo.setEmail(request.getParameter("email"));
		updateLoanInfo.setMobile(request.getParameter("mobile"));
		updateLoanInfo.setStatus("Submitted");

		connDao.uploadLoanByID(loanAccount, updateLoanInfo);
		  
		return "userhome1.jsp";
	}
	private String registerUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code to redirect page to read the user details */
		return "userhome1.jsp";
		
	}
	private String registernewuser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code to create the new user account read from user 
		   and return to index page */
		
		String loginuserid= request.getParameter("Nusername");
		String loginpassword= request.getParameter("Npassword");
		String loginemailid= request.getParameter("regemail");
		String loginMobileNum= request.getParameter("regContactnum");
		
		List<User> userRegistrationDetails = connDao.getUserRegistrationDetails(loginuserid);
			
		int arrSize = userRegistrationDetails.size();
		
		String strDBUserName = "";
		String strDBPassword = "";
		String jspname = "";
		
		if (arrSize > 0){
			User objDBUserName = userRegistrationDetails.get(0);
			strDBUserName = objDBUserName.getUsername();
			strDBPassword = objDBUserName.getPassword();}
		else {	
			strDBUserName = "";
			strDBPassword = "";}
		
		if(loginuserid.equalsIgnoreCase(strDBUserName) && loginpassword.equalsIgnoreCase(strDBPassword)) {
			jspname= "userhome1.jsp";	}
		else {
			connDao.addnewuser(loginuserid, loginpassword, loginemailid, loginMobileNum);
			jspname= "index.jsp";
			}
		return jspname;
	}
	
	private String register(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		/* write the code to redirect to register page */
		
		return "newuserui.jsp";
	}
	private String displaystatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code the display the loan status based on the given application
		   number 
		*/
		
		int loannumber= Integer.parseInt(request.getParameter("eLoanNumber"));
		
		List<LoanInfo> userLoanDetails = connDao.getLoanDetails(loannumber, "");
		
		int arrSize = userLoanDetails.size();
		
		int loanNumber = 0;
		String strLoanStatus = "";
		
		if (arrSize > 0){
			LoanInfo objLoanNumber = userLoanDetails.get(0);
			loanNumber = objLoanNumber.getApplno();
			strLoanStatus = objLoanNumber.getStatus();
			}
		else {
			loanNumber = 0;
			strLoanStatus = "No details found";}
		
		System.out.println("Loan Number -> " + loanNumber);
		System.out.println("Loan Status -> " + strLoanStatus);
		
		//Set the details back to the JSP Page
		request.setAttribute("loandbnumber", loanNumber);
		request.setAttribute("loandbstatus", strLoanStatus);
				
		return "displaystatus.jsp";
	}

	private String editloan(HttpServletRequest request, HttpServletResponse response) {
	// TODO Auto-generated method stub
	/* write a code to return to editloan page */
		
		int loanAccount= Integer.parseInt(request.getParameter("loanAccount"));
		LoanInfo userLoanDetails = connDao.getLoanDetailsByLoanID(loanAccount);
		request.setAttribute("loanDetails", userLoanDetails);
		
		return "editloanui.jsp";
	}

	private String trackloan(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write a code to return to trackloan page */
		
		return null;
	}

	private String application(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write a code to return to trackloan page */
		return "application.jsp";
	}
}