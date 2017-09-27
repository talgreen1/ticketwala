package com.att.ticketwala.startup;

import java.util.Arrays;
import java.util.Scanner;

import com.att.ticketwala.service.api.Command;
import com.att.ticketwala.service.api.CommandFactory;
import com.att.ticketwala.service.api.Customer;
import com.att.ticketwala.service.api.Result;
import com.att.ticketwala.service.api.TicketWalaService;
import com.att.ticketwala.service.impl.TicketWalaServiceImpl;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		Customer customer = new Customer("Yotam Avivi", "1234");
		TicketWalaService ticketService = new TicketWalaServiceImpl();
		
		String userCommandLine = "";
		
		
		System.out.println("Hello " + customer.getName() + "\n");
		System.out.println("You may choose from the following available shows:");
		System.out.println(Arrays.toString(ticketService.getAvailableShows()) + "\n");
		
		System.out.println("Enter your command.\n");
		System.out.println("Possible commands:\n");
		System.out.println("> create-order [show-name]");
		System.out.println("> add-seat [orderId] [row] [seat]");
		System.out.println("> submit-order [orderId]");
		System.out.println("> order-details [orderId]");
		System.out.println("> movie-show-status [showId]");
		
		while (!"quit".equals(userCommandLine)) {
			try {
				userCommandLine = scanner.nextLine();
				Command command = CommandFactory.createCommand(ticketService, userCommandLine);
				Result result = command.execute();
				System.out.println(result.getMessage());
			} catch (Exception e) {
				System.err.println("Unexpected Command Line Error! " + e.getMessage());
			}
		}
		
		System.out.println("Bye...");
		scanner.close();

	}


}
