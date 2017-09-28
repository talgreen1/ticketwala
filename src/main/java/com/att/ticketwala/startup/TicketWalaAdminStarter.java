package com.att.ticketwala.startup;

import java.util.Scanner;

import com.att.ticketwala.service.api.Command;
import com.att.ticketwala.service.api.CommandFactory;
import com.att.ticketwala.service.api.Result;
import com.att.ticketwala.service.api.TicketWalaService;
import com.att.ticketwala.service.api.User;
import com.att.ticketwala.service.impl.TicketWalaServiceImpl;

public class TicketWalaAdminStarter {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		User user = new User("Yotam Avivi", "1234");
		TicketWalaService ticketWalaService = new TicketWalaServiceImpl();
		
		String userCommandLine = "";
		
		System.out.println("Hello " + user.getName() + "\n");
		
		System.out.println("The following movies are currently stored:");
		System.out.println(ticketWalaService.getMovieShows() + "\n");
		
		System.out.println("Enter your command.\n");
		System.out.println("> add-movie-show [movie-name] [time:YYYY-MM-ddTHH:MM] [duration]");
		System.out.println("> delete-movie-show [show-id]");
		System.out.println("> list-movie-shows");
		
		while (!"quit".equals(userCommandLine)) {
			try {
				userCommandLine = scanner.nextLine();
				Command command = CommandFactory.createCommand(ticketWalaService, userCommandLine);
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
