package com.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
@Setter
public class Account {
	
	private Long accountId;
	private double accountBalance;
	private Long customerId;

}
