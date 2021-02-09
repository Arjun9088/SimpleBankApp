package com.example.models;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
@Setter
public class Customer {
	
	private Long id;
	private String firstName;
	private String lastName;
	private List<Account> accounts = new ArrayList<Account>();
	
	public Customer(Long id, String firstName, String lastName)
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public void addAccount(Account account)
	{
		accounts.add(account);
	}

}

