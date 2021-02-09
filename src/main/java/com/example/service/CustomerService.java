package com.example.service;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import com.example.models.Account;
import com.example.models.Customer;

public class CustomerService 
{
	
	Customer customer;
	Account account;
	static HashMap<Long, Account> accountsTable = new HashMap<Long, Account>();
	static HashMap<Long, Customer> customerTable = new HashMap<Long, Customer>();
	
	public void createCustomer()
	{
		String fname, lname;
		Long custId, accountId;
		double balance;
		Scanner scanner1 = new Scanner(System.in);
		Scanner scanner2 = new Scanner(System.in);
		Scanner scanner3 = new Scanner(System.in);
		System.out.print("Enter first name: ");
		fname = scanner1.nextLine();
		System.out.print("Enter last name: ");
		lname = scanner1.nextLine();
		System.out.print("Enter customer id: ");
		custId = scanner2.nextLong();
		System.out.print("Account Id: ");
		accountId = scanner2.nextLong();
		System.out.print("Enter account balance: ");
		balance = scanner3.nextDouble();
		customer = new Customer(custId, fname, lname);
		account = new Account(accountId, balance, custId);
		customer.addAccount(account);
		accountsTable.put(accountId, account);
		customerTable.put(custId, customer);
	}
	
	
	
	public void showAllCustomers()
	{
		Set<Long> setKeys = customerTable.keySet();
		for(Long keys: setKeys)
		{
			System.out.println(customerTable.get(keys).toString());
		}
	}
	
	
	
	public void showACustomer(Long custId)
	{
		Customer cust = customerTable.get(custId);
		if (cust == null)
		{
			System.out.println("--------------------Customer not found-----------------");
		}
		
		else
		{
			System.out.println(cust.getId());
			System.out.println(cust.getFirstName());
			System.out.println(cust.getLastName());
			for(Account accounts: cust.getAccounts())
			{
				System.out.println(account.getAccountId()+ " : " + accounts.getAccountBalance());
			}
		}
	}
	

}
