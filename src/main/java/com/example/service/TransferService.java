package com.example.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


import com.example.models.Account;
import com.opencsv.CSVWriter;

public class TransferService 
{
	CustomerService customerService = new CustomerService();
	AccountService accountService = new AccountService();
	static HashMap<Integer, List<Object>> transferTable = new HashMap<Integer, List<Object>>();
	Scanner scanLong = new Scanner(System.in);
	Scanner scanDouble = new Scanner(System.in);
	static CSVWriter writer;
	static Integer transferIds = 1;
	static
	{
		try
		{
			writer = new CSVWriter(new FileWriter("/home/sabs/output"));
		}
		catch(IOException e)
		{
			System.out.println("File error");
		}
	}
	
	
	
	public void withdrawMoney() throws IOException
	{
		
		Long accId;
		Double amount;
		System.out.print("Enter account Id: ");
		accId = scanLong.nextLong();
		if(CustomerService.accountsTable.containsKey(accId))
		{
			Account account = CustomerService.accountsTable.get(accId);
			System.out.print("Enter amount to be withdrawn: ");
			amount = scanDouble.nextDouble();
			if(account.getAccountBalance() < amount)
			{
				List<Object> transferDetails = new ArrayList<Object>();
				System.out.println("Insufficient Balance, Account only has " + account.getAccountBalance() );
				transferDetails.add(accId);
				transferDetails.add(accId);
				transferDetails.add(amount);
				transferDetails.add(new String("Failed"));
				transferTable.put(transferIds, transferDetails);
				transferIds++;
				for(Integer key: transferTable.keySet())
				{
					String [] lines = {key.toString(), transferTable.get(key).get(0).toString(), transferTable.get(key).get(1).toString(), transferTable.get(key).get(2).toString(), transferTable.get(key).get(3).toString()};
					writer.writeNext(lines);
				}
				writer.flush();
				return;
			}
			else
			{
				List<Object> transferDetails = new ArrayList<Object>();
				account.setAccountBalance(account.getAccountBalance()-amount);
				System.out.println("Withdrawn " + amount + ", Current balance " + account.getAccountBalance());
				transferDetails.add(accId);
				transferDetails.add(accId);
				transferDetails.add(amount);
				transferDetails.add(new String("Successfull"));
				transferTable.put(transferIds, transferDetails);
				transferIds++;
				for(Integer key: transferTable.keySet())
				{
					String [] lines = {key.toString(), transferTable.get(key).get(0).toString(), transferTable.get(key).get(1).toString(), transferTable.get(key).get(2).toString(), transferTable.get(key).get(3).toString()};
					writer.writeNext(lines);
				}
				writer.flush();
				
			}
		}
		else
		{
			System.out.println("Invalid account Id");
		}
	}
	
	public void depositMoney() throws IOException
	{
		Long accId;
		Double amount;
		System.out.print("Enter account Id: ");
		accId = scanLong.nextLong();
		if(CustomerService.accountsTable.containsKey(accId))
		{
			Account account = CustomerService.accountsTable.get(accId);
			System.out.print("Enter amount to be deposited: ");
			amount = scanDouble.nextDouble();
			account.setAccountBalance(account.getAccountBalance() + amount);
			System.out.println("Deposited " + amount + ", Current balance " + account.getAccountBalance());
			List<Object> transferDetails = new ArrayList<Object>();
			account.setAccountBalance(account.getAccountBalance()-amount);
			transferDetails.add(accId);
			transferDetails.add(accId);
			transferDetails.add(amount);
			transferDetails.add(new String("Successfull"));
			transferTable.put(transferIds, transferDetails);
			transferIds++;
			for(Integer key: transferTable.keySet())
			{
				String [] lines = {key.toString(), transferTable.get(key).get(0).toString(), transferTable.get(key).get(1).toString(), transferTable.get(key).get(2).toString(), transferTable.get(key).get(3).toString()};
				writer.writeNext(lines);
			}
			writer.flush();
		}
		else
		{
			System.out.println("Invalid account Id");
		}
	}

	public void transferBetweenAccounts() 
	{
		Long src, dest;
		double amt;
		System.out.print("Enter source account Id: ");
		src = scanLong.nextLong();
		System.out.print("Enter destination account Id: ");
		dest = scanLong.nextLong();
		if(src == dest)
		{
			System.out.println("Transfer within the same account not allowed");
			return;
		}
		System.out.print("Enter amount to be transferred: ");
		amt = scanDouble.nextDouble();
		if(amt > CustomerService.accountsTable.get(src).getAccountBalance())
		{
			System.out.println("------------------------Insufficient Balance-----------------------");
			return; 
		}
		else
		{
			CustomerService.accountsTable.get(dest).setAccountBalance(CustomerService.accountsTable.get(src).getAccountBalance()+amt);
			System.out.println("Transferred " + amt + " from Account " + src + "To " + dest + " Successfully");
		}
	}

	public void showAllTransactions()
	{
		System.out.println("Transfer Id-------Source Account-------Destination Account-------Status");
		for(Integer key: transferTable.keySet())
		{
			System.out.println(transferTable.get(key));
		}
	}
	
}
