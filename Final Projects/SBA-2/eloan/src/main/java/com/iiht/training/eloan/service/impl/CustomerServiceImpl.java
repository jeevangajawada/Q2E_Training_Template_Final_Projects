package com.iiht.training.eloan.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iiht.training.eloan.dto.LoanDto;
import com.iiht.training.eloan.dto.LoanOutputDto;
import com.iiht.training.eloan.dto.UserDto;
import com.iiht.training.eloan.entity.Loan;
import com.iiht.training.eloan.entity.Users;
import com.iiht.training.eloan.repository.LoanRepository;
import com.iiht.training.eloan.repository.ProcessingInfoRepository;
import com.iiht.training.eloan.repository.SanctionInfoRepository;
import com.iiht.training.eloan.repository.UsersRepository;
import com.iiht.training.eloan.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private ProcessingInfoRepository pProcessingInfoRepository;
	
	@Autowired
	private SanctionInfoRepository sanctionInfoRepository;
	
	// utility methods for User Dto
		private UserDto convertEntityToOutputDto(Users user) {
			UserDto userOutputDto = new UserDto();
			userOutputDto.setId(user.getId());
			userOutputDto.setFirstName(user.getFirstName());
			userOutputDto.setLastName(user.getLastName());
			userOutputDto.setEmail(user.getEmail());
			userOutputDto.setMobile(user.getMobile());
			return userOutputDto;
		}

		private Users covertInputDtoToEntity(UserDto userInputDto, String strInputRoleName) {
			Users user = new Users();
			//user.setId(userInputDto.getId());
			user.setFirstName(userInputDto.getFirstName());
			user.setLastName(userInputDto.getLastName());
			user.setEmail(userInputDto.getEmail());
			user.setMobile(userInputDto.getMobile());
			user.setRole(strInputRoleName);
			return user;
		}
	@Override
	public UserDto register(UserDto userDto) {
		// convert dto into entity
		Users registeruser = this.covertInputDtoToEntity(userDto, "Customer");
		// save entity in DB : returns the copy of newly added record back
		
		System.out.println("register user" + registeruser);
		
		Users newregisteruser = this.usersRepository.save(registeruser);
		// convert entity into output dto
		
		System.out.println("register new user" + newregisteruser);
		
		UserDto userOutputDto = this.convertEntityToOutputDto(newregisteruser);
		return userOutputDto;
	}
	
	//********************************************************************************//
	
	/*
	 * @Override public UserDto register(UserDto userDto) { // TODO Auto-generated
	 * method stub return null; }
	 */
	
	// utility methods for Loan Dto
		private LoanOutputDto convertEntityToOutputDtoLoan(Loan loan) {
			LoanOutputDto loanOutputDto = new LoanOutputDto();
			loanOutputDto.setCustomerId(loan.getCustomerId());
			loanOutputDto.setLoanAppId(loan.getId());
			loanOutputDto.setStatus("Applied");
			loanOutputDto.setRemark(loan.getRemark());
			return loanOutputDto;
		}

		private Loan covertInputDtoToEntityLoan(LoanDto loanInputDto, Long customerId) {
			Loan loan = new Loan();
			loan.setCustomerId(customerId);
			loan.setLoanName(loanInputDto.getLoanName());
			loan.setLoanAmount(loanInputDto.getLoanAmount());
			loan.setLoanApplicationDate(loanInputDto.getLoanApplicationDate());
			loan.setBusinessStructure(loanInputDto.getBusinessStructure());
			loan.setBillingIndicator(loanInputDto.getBillingIndicator());
			loan.setTaxIndicator(loanInputDto.getTaxIndicator());
			loan.setStatus(0);
			loan.setRemark("All Good"); // Giving comment as as 'All Good' as he is applying for loan newly
			return loan;
		}

	@Override
	public LoanOutputDto applyLoan(Long customerId, LoanDto loanDto) {
		Loan registerloan = this.covertInputDtoToEntityLoan(loanDto, customerId);
		
		// save entity in DB : returns the copy of newly added record back
		Loan newregisterloan = this.loanRepository.save(registerloan);
		
		// convert entity into output dto
		LoanOutputDto loanOutputDto = this.convertEntityToOutputDtoLoan(newregisterloan);
		return loanOutputDto;
	}

	@Override
	public LoanOutputDto getStatus(Long loanAppId) {
		// fetch record from DB
		Loan loan = this.loanRepository.findById(loanAppId).orElse(null);
		// convert entity into output dto
		LoanOutputDto loanOutputDto =  this.convertEntityToOutputDtoLoan(loan);
		return loanOutputDto;
	}

	@Override
	public List<LoanOutputDto> getStatusAll(Long customerId) {
		
		List<Loan> loans = this.loanRepository.findAll();
		List<LoanOutputDto> loanOutputDtos = 
				loans.stream()
							 .map(this :: convertEntityToOutputDtoLoan)
							 .collect(Collectors.toList());
		return loanOutputDtos;
		
		
	}

}
