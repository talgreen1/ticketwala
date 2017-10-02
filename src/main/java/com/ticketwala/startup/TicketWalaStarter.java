package com.ticketwala.startup;

import java.util.Scanner;

import com.ticketwala.command.api.Command;
import com.ticketwala.command.api.CommandFactory;
import com.ticketwala.command.api.Result;
import com.ticketwala.model.User;
import com.ticketwala.service.api.TicketWalaService;
import com.ticketwala.service.impl.TicketWalaServiceImpl;

public class TicketWalaStarter {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		User customer = new User("Yotam Avivi", "1234");
		TicketWalaService ticketService = new TicketWalaServiceImpl();
		
		String userCommandLine = "";
		
		
		System.out.println("Hello " + customer.getName() + "\n");
		System.out.println("You may choose from the following available shows:");
		System.out.println(ticketService.getMovieShows() + "\n");
		
		System.out.println("Enter your command.\n");
		System.out.println("Possible commands:\n");
		System.out.println("> create-order [show-id]");
		System.out.println("> add-seat [orderId] [row] [seat]");
		System.out.println("> submit-order [orderId]");
		System.out.println("> order-details [orderId]");
		System.out.println("> movie-show-status [showId]");
		
		while (!"quit".equals(userCommandLine)) {
			try {
				userCommandLine = scanner.nextLine();
				Command command = new CommandFactory().createCommand(ticketService, userCommandLine);
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
