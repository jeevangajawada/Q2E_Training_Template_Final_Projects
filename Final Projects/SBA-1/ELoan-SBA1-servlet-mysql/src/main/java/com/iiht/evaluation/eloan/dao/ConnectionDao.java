package com.iiht.evaluation.eloan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import com.iiht.evaluation.eloan.dto.LoanDto;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.User;

public class ConnectionDao {
	private static final long serialVersionUID = 1L;
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public ConnectionDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

	public  Connection connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
		return jdbcConnection;
	}

	public void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	// put the relevant DAO methods here..
	public List<User> getUserRegistrationDetails(String strInputUserName)
	{
		
		ArrayList<User> arrayList=new ArrayList<>();
		try {
			connect();
			System.out.println(jdbcConnection.isClosed());
			Statement userconnection = jdbcConnection.createStatement();
			ResultSet resultset=userconnection.executeQuery("select * from eloanregistrationpage where UserName = '" + strInputUserName + "'");
			
			while(resultset.next())
			{
				String username=resultset.getString("UserName");
				String password=resultset.getString("Password");
				String emailid=resultset.getString("MobileNumber");
				String modilenumber=resultset.getString("EmailID");
				arrayList.add(new User(username,password,emailid,modilenumber));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			{
				try {
					disconnect();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return arrayList;
		
	}
	
	public List<LoanInfo> getLoanDetails(int loannumber, String mobilenumber)
	{
		
		ArrayList<LoanInfo> arrayList=new ArrayList<>();
		try {
			connect();
			System.out.println(jdbcConnection.isClosed());
			Statement userconnection = jdbcConnection.createStatement();
			ResultSet resultset=userconnection.executeQuery
					("select * from loaninformation where Mobile = '" + mobilenumber + "' or AppNum = " + loannumber);
			
			while(resultset.next())
			{
				int loannumb=resultset.getInt("AppNum");
				String loanemail=resultset.getString("Email");
				String loanmobile=resultset.getString("Mobile");
				String loanstatus=resultset.getString("Status");
				int loanamount=resultset.getInt("AmountReq");
				
				arrayList.add(new LoanInfo(loannumb,loanemail,loanmobile,loanstatus, loanamount));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			{
				try {
					disconnect();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return arrayList;
		
	}
	
	public List<LoanInfo> getAllLoanDetails()
	{
		
		ArrayList<LoanInfo> arrayList=new ArrayList<>();
		try {
			connect();
			System.out.println(jdbcConnection.isClosed());
			Statement userconnection = jdbcConnection.createStatement();
			ResultSet resultset=userconnection.executeQuery
					("select * from loaninformation");
			
			while(resultset.next())
			{
				int loannumb=resultset.getInt("AppNum");
				String loanpurpose=resultset.getString("LoanPurpose");
				int loanamount=resultset.getInt("AmountReq");
				String loanStatus=resultset.getString("Status");
				
				arrayList.add(new LoanInfo(loannumb, loanpurpose, loanamount, loanStatus));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			{
				try {
					disconnect();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return arrayList;
		
	}
	
	public void addnewuser(String strUName, String UPassword, String UEmail, String UMobile)  {

		if (strUName != null && UPassword != null && UEmail != null && UMobile != null) {
			try {
				connect();
				PreparedStatement preparedstatement = jdbcConnection.prepareStatement
						("INSERT INTO eloanregistrationpage"
								+ "(UserName,Password,MobileNumber,EmailID) VALUES(?,?,?,?)");

				preparedstatement.setString(1, strUName);
				preparedstatement.setString(2, UPassword);
				preparedstatement.setString(3, UMobile);
				preparedstatement.setString(4, UEmail);
				
				preparedstatement.executeUpdate();
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public LoanInfo getLoanDetailsByLoanID(int loanid )  {

		LoanInfo loanDetailsDB = new LoanInfo();
		
			try {
				connect();
				PreparedStatement pst = jdbcConnection.prepareStatement("select * from loaninformation where AppNum=?");
			
				pst.setInt(1, loanid);
				
				ResultSet executeQuery = pst.executeQuery();
				
				if(executeQuery.next()) {
					loanDetailsDB.setApplno(executeQuery.getInt(1));
					loanDetailsDB.setPurpose(executeQuery.getString(2));
					loanDetailsDB.setAmtrequest(executeQuery.getInt(3));
					loanDetailsDB.setDoa(executeQuery.getString(4));
					loanDetailsDB.setBstructure(executeQuery.getString(5));
					loanDetailsDB.setBstructure(executeQuery.getString(6));
					loanDetailsDB.setTindicator(executeQuery.getString(7));
					loanDetailsDB.setAddress(executeQuery.getString(8));
					loanDetailsDB.setEmail(executeQuery.getString(9));
					loanDetailsDB.setMobile(executeQuery.getString(10));
					loanDetailsDB.setStatus(executeQuery.getString(11));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return loanDetailsDB;
	}
	
	public LoanInfo uploadLoanByID(int loanid,LoanInfo UpdateLoanMaster)  {
		if (UpdateLoanMaster != null) {
			try {
				connect();
				PreparedStatement pst = jdbcConnection.prepareStatement("UPDATE loaninformation set "
								+ "AppNum=?,LoanPurpose=?,AmountReq=?,"
								+ "DateOfApp=?,BusinessStructure=?,BillingIndicator=?,"
								+ "TaxIndicator=?,Address=?,Email=?,"
								+ "Mobile=?,Status=?"
						+ "where AppNum=" + loanid);
				
				pst.setInt(1, loanid);
				pst.setString(2, UpdateLoanMaster.getPurpose());
				pst.setInt(3, UpdateLoanMaster.getAmtrequest());
				pst.setString(4, UpdateLoanMaster.getDoa());
				pst.setString(5, UpdateLoanMaster.getBstructure());
				pst.setString(6, UpdateLoanMaster.getBindicator());
				pst.setString(7, UpdateLoanMaster.getTindicator());
				pst.setString(8, UpdateLoanMaster.getAddress());
				pst.setString(9, UpdateLoanMaster.getEmail());
				pst.setString(10, UpdateLoanMaster.getMobile());
				pst.setString(11, UpdateLoanMaster.getStatus());
			
				pst.executeUpdate();
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return UpdateLoanMaster;
	}
	
	public void updateLoanStatus(int loanid)  {
		if (loanid > 0) {
			try {
				connect();
				PreparedStatement pst = jdbcConnection.prepareStatement("UPDATE loaninformation set Status='Approved'"
						+ "where AppNum=" + loanid);
				pst.executeUpdate();
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void addNewLoan(String loanname,
			int loanamtrequested, String appdate, String busstructure, 
			String billingindicator, String taxindicator, String address,
			String email, String mobile, String status)  {

		if (loanamtrequested > 0 && mobile != null && email != null) {
			try {
				connect();
				PreparedStatement preparedstatement = jdbcConnection.prepareStatement
						("INSERT INTO loaninformation(LoanPurpose,AmountReq,"
								+ "DateOfApp,BusinessStructure,BillingIndicator,"
								+ "TaxIndicator,Address,Email,"
								+ "Mobile,Status) VALUES(?,?,?,?,?,?,?,?,?,?)");

				preparedstatement.setString(1, loanname);
				preparedstatement.setInt(2, loanamtrequested);
				preparedstatement.setString(3, appdate);
				preparedstatement.setString(4, busstructure);
				preparedstatement.setString(5, billingindicator);
				preparedstatement.setString(6, taxindicator);
				preparedstatement.setString(7, address);
				preparedstatement.setString(8, email);
				preparedstatement.setString(9, mobile);
				preparedstatement.setString(10, status);
				
				preparedstatement.executeUpdate();
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
