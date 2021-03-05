package com.example.demo.servcie;

import com.example.demo.dto.CustomerRequestFundTransferDTO;
import com.example.demo.dto.CustomerResponseFundTransferDTO;

/**
 * @author Rajkumar Banala 27-Feb-2021
 *
 */

public interface FundTransferService {
	
	public CustomerResponseFundTransferDTO fundTransfer(CustomerRequestFundTransferDTO customerRequestFundTransferDTO);
}
