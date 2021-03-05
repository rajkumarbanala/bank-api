package com.example.demo.servcie.impl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.CustomerRequestFundTransferDTO;
import com.example.demo.dto.CustomerResponseFundTransferDTO;
import com.example.demo.entity.Account;
import com.example.demo.entity.AccountTransactions;
import com.example.demo.enums.EnumsUtil.TransactionStatus;
import com.example.demo.enums.EnumsUtil.TransactionType;
import com.example.demo.exception.AppBaseException;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.AccountTransactionsRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.servcie.FundTransferService;

/**
 * @author Rajkumar Banala 27-Feb-2021
 *
 */

@Service
public class FundTransferServiceImpl implements FundTransferService {
	
	private static final Logger LOG = LoggerFactory.getLogger(FundTransferServiceImpl.class);
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AccountTransactionsRepository accountTransactionsRepository;
	
	@Transactional(readOnly = false)
	@Override
	public CustomerResponseFundTransferDTO fundTransfer(CustomerRequestFundTransferDTO customerRequestFundTransferDTO) {
		LOG.debug("fundTransfer()");
		
		// inputs
		Long fromAccountNumber = customerRequestFundTransferDTO.getFromAccountNumber();
		Long toAccountNumber = customerRequestFundTransferDTO.getToAccountNumber();
		Double amount = customerRequestFundTransferDTO.getAmount();
		String customerComments = customerRequestFundTransferDTO.getCustomerComments();
		
		// check fromAccont
		Account fromAccountDB = accountRepository.findByAccountNumber(fromAccountNumber);
		
		if(fromAccountDB == null)
			throw new AppBaseException("fromAccount not found");
		
		// check balance
		if (amount > fromAccountDB.getBalance())
			throw new AppBaseException("Insufficient balance");
		
		// check toAccount
		Account toAccountDB = accountRepository.findByAccountNumber(toAccountNumber);
		
		if(toAccountDB == null)
			throw new AppBaseException("toAccount not found");
		
		// debit amount from fromAccount
		double fromAccountBalanceDB = fromAccountDB.getBalance();
		
		fromAccountBalanceDB -= amount;
		fromAccountDB.setBalance(fromAccountBalanceDB);
		
		// create transaction for fromAccount
		AccountTransactions fromAccountTransactionsNew = new AccountTransactions();
		fromAccountTransactionsNew.setAccountId(fromAccountDB.getId());
		fromAccountTransactionsNew.setTransactionType(TransactionType.DEBIT);
		fromAccountTransactionsNew.setTransactionStatus(TransactionStatus.SUCCESS);
		fromAccountTransactionsNew.setTransactionComments("Amount Dedited");
		fromAccountTransactionsNew.setCustomerComments(customerComments);
		fromAccountTransactionsNew.setAmount(amount);
		fromAccountTransactionsNew.setSourceAccountNumber(toAccountNumber);
		fromAccountTransactionsNew.setTransactionDate(LocalDateTime.now());
		
		// credit amount to toAccount
		Double toAccountBalance = toAccountDB.getBalance();
		
		toAccountBalance += amount;
		toAccountDB.setBalance(toAccountBalance);
		
		// create transaction for toAccount
		AccountTransactions toAccountTransactionsNew = new AccountTransactions();
		toAccountTransactionsNew.setAccountId(toAccountDB.getId());
		toAccountTransactionsNew.setTransactionType(TransactionType.CREDIT);
		toAccountTransactionsNew.setTransactionStatus(TransactionStatus.SUCCESS);
		toAccountTransactionsNew.setTransactionComments("Amount Credited");
		toAccountTransactionsNew.setCustomerComments(customerComments);
		toAccountTransactionsNew.setAmount(amount);
		toAccountTransactionsNew.setSourceAccountNumber(fromAccountNumber);
		toAccountTransactionsNew.setTransactionDate(LocalDateTime.now());
		
		// update in DB
		fromAccountTransactionsNew = accountTransactionsRepository.save(fromAccountTransactionsNew);
		
		if(fromAccountTransactionsNew == null)
			throw new AppBaseException("Transaction failed");
		
		toAccountTransactionsNew = accountTransactionsRepository.save(toAccountTransactionsNew);
		
		if(toAccountTransactionsNew == null)
			throw new AppBaseException("Transaction failed");
		
		fromAccountDB = accountRepository.save(fromAccountDB);
		
		if(fromAccountDB == null)
			throw new AppBaseException("Transaction failed");

		fromAccountDB = accountRepository.save(fromAccountDB);
		
		if(fromAccountDB == null)
			throw new AppBaseException("Transaction failed");
		
		// prepate response
		CustomerResponseFundTransferDTO customerResponseFundTransferDTO = new CustomerResponseFundTransferDTO();
		customerResponseFundTransferDTO.setFromAccountNumber(fromAccountNumber);
		customerResponseFundTransferDTO.setToAccountNumber(toAccountNumber);
		customerResponseFundTransferDTO.setAmount(amount);
		customerResponseFundTransferDTO.setCustomerComments(customerComments);
		customerResponseFundTransferDTO.setTransactionStatus(TransactionStatus.SUCCESS);
		customerResponseFundTransferDTO.setTransactionComments("Amount Transfered Successfully");
		
		return customerResponseFundTransferDTO;
	}
}
