package com.ticketwala.startup;

import java.util.Scanner;

public class TicketWalaStarter {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		String userCommandLine = "";
		
		System.out.println("Enter your command:\n");
		
		while (!"bye".equals(userCommandLine)) {
			try {
				userCommandLine = scanner.nextLine();
				String[] commandArray = userCommandLine.trim().split("\\s+"); //Split on white spaces of any size
				
				System.out.println("Command : " + commandArray[0]);
				for (int i = 1; i < commandArray.length; i++) {
					System.out.println("Arg" + i + " : " + commandArray[i]);
				}
			} catch (Exception e) {
				System.err.println("Unexpected Command Line Error! " + e.getMessage());
			}
		}
		
		System.out.println("Bye...");
		scanner.close();		
	}

}
