package com.iiht.training.eloan.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.eloan.dto.LoanDto;
import com.iiht.training.eloan.dto.LoanOutputDto;
import com.iiht.training.eloan.dto.ProcessingDto;
import com.iiht.training.eloan.entity.Loan;
import com.iiht.training.eloan.repository.LoanRepository;
import com.iiht.training.eloan.repository.ProcessingInfoRepository;
import com.iiht.training.eloan.repository.SanctionInfoRepository;
import com.iiht.training.eloan.repository.UsersRepository;
import com.iiht.training.eloan.service.ClerkService;

@Service
public class ClerkServiceImpl implements ClerkService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private ProcessingInfoRepository pProcessingInfoRepository;
	
	@Autowired
	private SanctionInfoRepository sanctionInfoRepository;
	
	private LoanOutputDto convertEntityToOutputDtoLoan(Loan loan) {
		LoanOutputDto loanOutputDto = new LoanOutputDto();
		loanOutputDto.setCustomerId(loan.getCustomerId());
		loanOutputDto.setLoanAppId(loan.getId());
		loanOutputDto.setStatus(loan.getStatus().toString());
		loanOutputDto.setRemark(loan.getRemark());
		return loanOutputDto;
	}
	
	@Override
	public List<LoanOutputDto> allAppliedLoans()
	{
		List<Loan> loans = this.loanRepository.findAllLoansByStatus(0);
		List<LoanOutputDto> loanOutputDtos = 
				loans.stream()
							 .map(this :: convertEntityToOutputDtoLoan)
							 .collect(Collectors.toList());
		return loanOutputDtos;
	}

	
	private Loan covertInputDtoToEntityProcessLoan(LoanDto loanInputDto, ProcessingDto processingDto) {
		Loan loan = new Loan();
		loan.setLoanName(loanInputDto.getLoanName());
		loan.setLoanAmount(loanInputDto.getLoanAmount());
		loan.setLoanApplicationDate(loanInputDto.getLoanApplicationDate());
		loan.setBusinessStructure(loanInputDto.getBusinessStructure());
		loan.setBillingIndicator(loanInputDto.getBillingIndicator());
		loan.setTaxIndicator(loanInputDto.getTaxIndicator());
		loan.setStatus(1);
		loan.setRemark("Manager Approval Pending"); // Giving comment as as 'All Good' as he is applying for loan newly
		return loan;
	}
	
	private ProcessingDto convertEntityToOutputDtoProcessLoan(Loan loan) {
		LoanOutputDto loanOutputDto = new LoanOutputDto();
		ProcessingDto loanProcessingDto = new ProcessingDto();
		loanOutputDto.setCustomerId(loan.getCustomerId());
		loanOutputDto.setLoanAppId(loan.getId());
		loanOutputDto.setStatus("Processed");
		loanOutputDto.setRemark(loan.getRemark());
		return loanProcessingDto;
	}
	
	@Override
	public ProcessingDto processLoan(Long clerkId, Long loanAppId, ProcessingDto processingDto) {
				
		// convert dto into entity
		LoanDto loanDto = new LoanDto();
		Loan loan = this.covertInputDtoToEntityProcessLoan(loanDto,processingDto);
		// assign the id to update
		loan.setId(loanAppId);
		// repository method to update
		Loan updatedLoan = this.loanRepository.save(loan);
		// convert entity into output dto
		ProcessingDto processingOutputDto = this.convertEntityToOutputDtoProcessLoan(updatedLoan);
		return processingOutputDto;
				
	}

}
