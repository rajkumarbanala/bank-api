package com.example.demo.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CustomerRequestStatementDTO;
import com.example.demo.dto.CustomerResponseStatementDTO;
import com.example.demo.servcie.StatementService;

/**
 * @author Rajkumar Banala 15-Feb-2021
 *
 */

@RestController
@RequestMapping("/api/statement")
@Validated
public class StatementController {
	
	private static final Logger LOG = LoggerFactory.getLogger(StatementController.class);
	
	@Autowired
	private StatementService statementService;
	
	@PostMapping("/{customerId}")
	CustomerResponseStatementDTO generateStatement(@PathVariable @Min(1) @Max(10) Long customerId, @RequestBody CustomerRequestStatementDTO customerRequestStatementDTO) {
		LOG.debug("generateStatement()");
		
		return statementService.generateStatement(customerId, customerRequestStatementDTO);
	}
}
