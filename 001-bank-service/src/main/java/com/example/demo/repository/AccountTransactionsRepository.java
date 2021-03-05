package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.CustomerResponseTransactionsDTO;
import com.example.demo.entity.AccountTransactions;

/**
 * @author Rajkumar Banala 15-Feb-2021
 *
 */

@Repository
public interface AccountTransactionsRepository extends JpaRepository<AccountTransactions, Long> {
	
	List<AccountTransactions> findByAccountId(Long accountId);
	
	@Query("select new com.example.demo.dto.CustomerResponseTransactionsDTO(transactionType, transactionStatus, transactionComments, customerComments, amount, sourceAccountNumber, transactionDate) from AccountTransactions where accountId=:accountId and date(transactionDate)>=date(:fromDate)")
	List<CustomerResponseTransactionsDTO> getByAccountIdAndFromDate(Long accountId, String fromDate);
	
	@Query("select new com.example.demo.dto.CustomerResponseTransactionsDTO(transactionType, transactionStatus, transactionComments, customerComments, amount, sourceAccountNumber, transactionDate) from AccountTransactions where accountId=:accountId and date(transactionDate)<=date(:toDate)")
	List<CustomerResponseTransactionsDTO> getByAccountIdAndToDate(Long accountId, String toDate);
	
	@Query("select new com.example.demo.dto.CustomerResponseTransactionsDTO(transactionType, transactionStatus, transactionComments, customerComments, amount, sourceAccountNumber, transactionDate) from AccountTransactions where accountId=:accountId and date(transactionDate) between date(:fromDate) and date(:toDate)")
	List<CustomerResponseTransactionsDTO> getByAccountId(Long accountId, String fromDate, String toDate);
}
