package com.example.service;

import java.io.IOException;
import java.util.Scanner;

public class Execution 
{
	CustomerService custServ = new CustomerService();
	TransferService transferService = new TransferService();
	
	public void executeProgram() throws IOException
	{
		int choice;
		Scanner scanner = new Scanner(System.in);
		Scanner scanL = new Scanner(System.in);
		Long custId;
		while(true)
		{
			if(CustomerService.customerTable.size() == 0)
			{
				System.out.println("Welcome to Bank");
				System.out.println("1.Create Customer\n9.Exit");

			}
			else
			{
			System.out.println("Welcome to Bank");
			System.out.println("1.Create Customer\n2.Show all Customers\n3.Find Customer\n4.Withdraw\n5.Deposit\n6.Transfer\n7.Transfer Logs\n9.Exit");
			}
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();
			switch(choice)
			{
			case 1:
				custServ.createCustomer();
				break;
			case 2:
				custServ.showAllCustomers();
				break;
			case 3:
				System.out.println("Enter customer id");
				custId = scanL.nextLong();
				custServ.showACustomer(custId);
				break;
			case 4:
				transferService.withdrawMoney();
				break;
			case 5:
				transferService.depositMoney();
				break;	
			case 6:
				transferService.transferBetweenAccounts();
				break;
			case 7:
				transferService.showAllTransactions();
				break;
			case 9:
				System.out.println("Exiting...");
				System.exit(0);
			default:
				System.out.println("Invalid choice: ");
				break;
			}
			System.out.println("**********************************************************************************************************************************************************************");
		}
		
	}

}
