package com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.dto.LoanDto;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;


@WebServlet("/admin")
public class AdminController extends HttpServlet {
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
		String action =  request.getParameter("action");
		System.out.println(action);
		String viewName = "";
		try {
			switch (action) {
			case "adminlogin" : 
				viewName = adminLogin(request, response);
				break;
			case "listall" : 
				viewName = listall(request, response);
				break;
			case "process":
				viewName=process(request,response);
				break;
			case "callemi":
				viewName=calemi(request,response);
				break;
			case "updatestatus":
				viewName=updatestatus(request,response);
				break;
			case "logout":
				viewName = adminLogout(request, response);
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

	private String updatestatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code for updatestatus of loan and return to admin home page */
		
		return null;
	}
	private String calemi(HttpServletRequest request, HttpServletResponse response) throws SQLException {
	// TODO Auto-generated method stub
	/* write the code to calculate emi for given applno and display the details */
		
		int loannumber= Integer.parseInt(request.getParameter("eLoanNumber"));
		connDao.updateLoanStatus(loannumber);
		
		List<LoanInfo> userLoanDetails = connDao.getLoanDetails(loannumber, "");
		
		int arrSize = userLoanDetails.size();
		
		int loanNumber = 0;
		String strLoanStatus = "";
		int loanAmountReq = 0;
		
		if (arrSize > 0){
			LoanInfo objLoanNumber = userLoanDetails.get(0);
			loanNumber = objLoanNumber.getApplno();
			strLoanStatus = objLoanNumber.getStatus();
			loanAmountReq = objLoanNumber.getAmtrequest();
			}
		else {
			loanNumber = 0;
			strLoanStatus = "No details found";
			loanAmountReq = 0;
			}
		
		//Set the details back to the JSP Page
		request.setAttribute("loandbnumber", loanNumber);
		request.setAttribute("loandbstatus", strLoanStatus);
		request.setAttribute("loandbamount", loanAmountReq);
		
		//Add more details to the EMI Page
		int loanAmountSanctioned = 25000;
		int loanTerm = 20;
		int TermPaymentAmount = (loanAmountSanctioned ) * (1 + 9/100) ^ (20);
		int MonthlyPayment = TermPaymentAmount/loanTerm;
		
		Calendar calendar = Calendar.getInstance();
		System.out.println("Payment Start Date -> " + calendar.getTime());
		request.setAttribute("paymentstartdate", calendar.getTime());
		calendar.add(Calendar.YEAR, 20);
		System.out.println("Loan Closure Date -> " + calendar.getTime());
		request.setAttribute("paymentclosuredate", calendar.getTime());
		System.out.println("Monthly Payment Amount -> " + MonthlyPayment);
		request.setAttribute("loanterinyears", loanTerm);
		request.setAttribute("MonthlyPayment", MonthlyPayment);
		
		
		return "calemi.jsp";
	}
	private String process(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
	/* return to process page */
		return  "process.jsp";
	}
	private String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write code to return index page */
		return null;
	}

	private String listall(HttpServletRequest request, HttpServletResponse response) throws SQLException {
	/* write the code to display all the loans */
		
		List<LoanInfo> loanInfoDetails= connDao.getAllLoanDetails();
		request.setAttribute("loanInfoDetails", loanInfoDetails);
		
		return "listall.jsp";
	}

	private String adminLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String loginuserid= request.getParameter("loginuserid");
		String loginpwd= request.getParameter("password");
		String jspname;
		
		if(loginuserid.equalsIgnoreCase("admin") && loginpwd.equalsIgnoreCase("admin")) {
			jspname= "adminhome1.jsp";	}
		else {
			jspname="errorPage.jsp";}
		return jspname;
	}
	
}