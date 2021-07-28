package com.example.demo.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
public class CustomerRequestFundTransferDTO {
	
	@Min(value = 1)
	@Max(value = 999999999)
	@NotNull(message = "fromAccountNumber is mandatory.")
	private Long fromAccountNumber;
	
	@Min(value = 1)
	@Max(value = 999999999)
	@NotNull(message = "toAccountNumber is mandatory.")
	private Long toAccountNumber;

	@Positive
	private double amount;
	
	private String customerComments;
}
