package com.example.demo.dto;

import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.example.demo.enums.EnumsUtil.TransactionStatus;
import com.example.demo.enums.EnumsUtil.TransactionType;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Rajkumar Banala 15-Feb-2021
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerResponseTransactionsDTO {
	
	// FUND_TRANSFER_CREDIT, FUND_TRANSFER_DEBIT
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	
	// SUCCESS, FAILED
	private TransactionStatus transactionStatus;
	
	private String transactionComments;
	
	private String customerComments;
	
	private double amount;
	
	private Long sourceAccountNumber;
	
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private LocalDateTime transactionDate;
}
