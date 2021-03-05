package com.example.demo.servcie.impl;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dto.CustomerRequestStatementDTO;
import com.example.demo.dto.CustomerResponseStatementDTO;
import com.example.demo.dto.CustomerResponseTransactionsDTO;
import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.exception.AppBaseException;
import com.example.demo.repository.AccountTransactionsRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.servcie.StatementService;

/**
 * @author Rajkumar Banala 27-Feb-2021
 *
 */

@Service
public class StatementServiceImpl implements StatementService {
	
	private static final Logger LOG = LoggerFactory.getLogger(StatementServiceImpl.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountTransactionsRepository accountTransactionsRepository;
	
	@Override
	public CustomerResponseStatementDTO generateStatement(Long customerId, CustomerRequestStatementDTO customerRequestStatementDTO) {
		LOG.debug("generateStatement()");
		
		// check customer
		Optional<Customer> customerOptional = customerRepository.findById(customerId);
		
		if(!customerOptional.isPresent())
			throw new AppBaseException("Customer not found");
		
		Customer customer = customerOptional.get();
		
		// check account
		Account account = customer.getAccount();
		
		if(account == null)
			throw new AppBaseException("account not found for this customer");
		
		// inputs
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fromDate = null;
		String toDate = null;
		
		if(customerRequestStatementDTO.getFromDate() != null)
			fromDate = customerRequestStatementDTO.getFromDate().format(dateTimeFormatter);
		
		if(customerRequestStatementDTO.getToDate() != null)
			toDate = customerRequestStatementDTO.getToDate().format(dateTimeFormatter);
		
		LOG.debug("generateStatement().fromDate:" + fromDate);
		LOG.debug("generateStatement().toDate:" + toDate);
		
		List<CustomerResponseTransactionsDTO> list = null;
		
		if(StringUtils.hasText(fromDate) && StringUtils.hasText(toDate) )
			list = accountTransactionsRepository.getByAccountId(account.getId(), fromDate, toDate);
		else if(StringUtils.hasText(fromDate) && !StringUtils.hasText(toDate) )
			list = accountTransactionsRepository.getByAccountIdAndFromDate(account.getId(), fromDate);
		else if(!StringUtils.hasText(fromDate) && StringUtils.hasText(toDate) )
			list = accountTransactionsRepository.getByAccountIdAndToDate(account.getId(), toDate);
		
		// prepare response
		CustomerResponseStatementDTO customerResponseStatementDTO = new CustomerResponseStatementDTO();
		customerResponseStatementDTO.setFirstName(customer.getFirstName());
		customerResponseStatementDTO.setLastName(customer.getLastName());
		customerResponseStatementDTO.setBalance(account.getBalance());
		customerResponseStatementDTO.setAccountNumber(account.getAccountNumber());
		customerResponseStatementDTO.setTransactions(list);
		
		return customerResponseStatementDTO;
	}
}
