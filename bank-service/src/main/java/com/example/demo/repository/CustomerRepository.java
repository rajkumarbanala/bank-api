package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Customer;

/**
 * @author Rajkumar Banala 15-Feb-2021
 *
 */

public interface CustomerRepository  extends JpaRepository<Customer, Long> {

}
