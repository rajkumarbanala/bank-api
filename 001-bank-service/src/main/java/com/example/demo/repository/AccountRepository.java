package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Account;

/**
 * @author Rajkumar Banala 15-Feb-2021
 *
 */

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	Account findByAccountNumber(Long accountNumber);
	
//	Account findByCustomerId(Long customerId);
}
