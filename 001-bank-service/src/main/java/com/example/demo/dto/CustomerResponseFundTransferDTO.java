package com.example.demo.dto;

import com.example.demo.enums.EnumsUtil.TransactionStatus;

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
@ToString
public class CustomerResponseFundTransferDTO {
	
	private Long fromAccountNumber;
	
	private Long toAccountNumber;
	
	private double amount;
	
	private String customerComments;
	
	// SUCCESS, FAILED
	private TransactionStatus transactionStatus;
	
	private String transactionComments;
}
