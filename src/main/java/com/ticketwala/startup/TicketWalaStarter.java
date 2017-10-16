package com.ticketwala.startup;

import java.util.Scanner;

import com.ticketwala.command.api.Command;
import com.ticketwala.command.api.CommandFactory;
import com.ticketwala.command.api.Result;
import com.ticketwala.service.api.TicketWalaService;
import com.ticketwala.service.impl.TicketWalaServiceImpl;

public class TicketWalaStarter {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		TicketWalaService tws = new TicketWalaServiceImpl();
		
		String userCommandLine = "";
		
		System.out.println("Enter your command:\n");
		System.out.println("Possible commands:\n");
		System.out.println("> create-order [show-id]");
		System.out.println("> add-seat [orderId] [row] [seat]");
		System.out.println("> submit-order [orderId]");
		System.out.println("> print-movie-show [showId]");
		System.out.println("> print-all-movie-shows");
//		System.out.println("> print-order [orderId]");
		
		CommandFactory commandFactory = new CommandFactory();
		
		while (!"bye".equals(userCommandLine)) {
			try {
				
				userCommandLine = scanner.nextLine();
				
				//Use Command Factory that will create a suitable command according to user input 
                Command command = commandFactory.createCommand(tws, userCommandLine);
                
                //Execute command and display result
                Result result = command.execute();
                System.out.println(result.getMessage());
                
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Unexpected Command Line Error! " + e.getMessage());
			}
		}
		
		System.out.println("Bye...");
		scanner.close();		
	}

}
