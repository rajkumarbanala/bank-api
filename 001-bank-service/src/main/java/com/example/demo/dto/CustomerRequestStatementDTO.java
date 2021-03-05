package com.example.demo.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class CustomerRequestStatementDTO {
	
//	@JsonSerialize(using=JsonUtilLocalDateTimeSerializer.class)
//	@JsonDeserialize(using=JsonUtilLocalDateTimeDeserializer.class)
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate fromDate;

//	@JsonSerialize(using=JsonUtilLocalDateTimeSerializer.class)
//	@JsonDeserialize(using=JsonUtilLocalDateTimeDeserializer.class)
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate toDate;
}
