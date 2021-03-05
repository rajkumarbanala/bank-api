package com.example.demo.handler;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Rajkumar Banala 16-Feb-2021
 *
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorResponse {
	
	private int errorCode;
	
	private String errorMessage;
	
	private LocalDateTime timeStamp;
	
	private Map<String, String> errors;
}
