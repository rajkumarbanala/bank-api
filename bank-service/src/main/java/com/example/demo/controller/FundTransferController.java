package com.example.demo.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CustomerRequestFundTransferDTO;
import com.example.demo.dto.CustomerResponseFundTransferDTO;
import com.example.demo.servcie.FundTransferService;

/**
 * @author Rajkumar Banala 15-Feb-2021
 *
 */

@RestController
@RequestMapping("/api/fundTransafer")
@Validated
public class FundTransferController {
	
	private static final Logger LOG = LoggerFactory.getLogger(FundTransferController.class);
	
	@Autowired
	private FundTransferService fundTransferService;
	
	@PostMapping()
	CustomerResponseFundTransferDTO fundTransfer(@Valid @RequestBody CustomerRequestFundTransferDTO customerRequestFundTransferDTO) {
		LOG.debug("fundTransfer()");
		
		return fundTransferService.fundTransfer(customerRequestFundTransferDTO);
	}
}
