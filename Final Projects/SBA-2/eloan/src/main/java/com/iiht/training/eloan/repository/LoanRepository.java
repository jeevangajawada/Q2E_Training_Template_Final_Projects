package com.iiht.training.eloan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iiht.training.eloan.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long>{
	
	/*
	 * //Static query
	 * 
	 * @Query("Select u from Loan u where u.status=0") public List<Loan>
	 * findAllAppliedLoans();
	 */
	
	//Dynamic query
	@Query("Select u from Loan u where u.status=?1")
	public List<Loan> findAllLoansByStatus(int status);

}
