package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.enums.EnumsUtil.TransactionStatus;
import com.example.demo.enums.EnumsUtil.TransactionType;
import com.example.demo.util.JsonUtilLocalDateTimeDeSerializer;
import com.example.demo.util.JsonUtilLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
@Entity
@Table(name = "account_transactions")
public class AccountTransactions {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(name = "account_id")
	private Long accountId;
	
	// FUND_TRANSFER_CREDIT, FUND_TRANSFER_DEBIT
	@Column(name = "transaction_type")
	private TransactionType transactionType;
	
	// SUCCESS, FAILED
	@Column(name = "transaction_status")
	private TransactionStatus transactionStatus;
	
	@Column(name = "transaction_comments")
	private String transactionComments;
	
	@Column(name = "customer_comments")
	private String customerComments;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "source_account_number")
	private Long sourceAccountNumber;
	
	@JsonSerialize(using = JsonUtilLocalDateTimeSerializer.class)
	@JsonDeserialize(using = JsonUtilLocalDateTimeDeSerializer.class)
	@Column(name = "transaction_date")
	private LocalDateTime transactionDate;
}
