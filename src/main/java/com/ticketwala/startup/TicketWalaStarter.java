package com.ticketwala.startup;

import java.util.Scanner;

import com.ticketwala.command.api.Command;
import com.ticketwala.command.api.CommandFactory;
import com.ticketwala.command.api.Result;

public class TicketWalaStarter {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		String userCommandLine = "";
		
		System.out.println("Enter your command:\n");
		
		CommandFactory commandFactory = new CommandFactory();
		
		while (!"bye".equals(userCommandLine)) {
			try {
				
				userCommandLine = scanner.nextLine();
				
				//Use Command Factory that will create a suitable command according to user input 
                Command command = commandFactory.createCommand(userCommandLine);
                
                //Execute command and display result
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
