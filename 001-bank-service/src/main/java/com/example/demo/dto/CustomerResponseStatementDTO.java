package com.example.demo.dto;

import java.util.List;

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
public class CustomerResponseStatementDTO {
	
	private String firstName;
	
	private String lastName;
	
	private Long accountNumber;
	
	private double balance;
	
	private List<CustomerResponseTransactionsDTO> transactions;
}
