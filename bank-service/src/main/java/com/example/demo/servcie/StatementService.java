package com.example.demo.servcie;

import com.example.demo.dto.CustomerRequestStatementDTO;
import com.example.demo.dto.CustomerResponseStatementDTO;

/**
 * @author Rajkumar Banala 27-Feb-2021
 *
 */

public interface StatementService {
	
	public CustomerResponseStatementDTO generateStatement(Long customerId, CustomerRequestStatementDTO customerRequestStatementDTO);
}
