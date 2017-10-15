package com.ticketwala.command.api;

import java.util.Arrays;
import java.util.List;

import com.ticketwala.command.impl.EchoCommand;
import com.ticketwala.command.impl.InvalidCommand;
import com.ticketwala.command.impl.PrintAllMovieShowsCommand;
import com.ticketwala.command.impl.PrintMovieShowCommand;
import com.ticketwala.service.api.TicketWalaService;

public class CommandFactory {
	public Command createCommand(TicketWalaService tws, String commandLine) {
		Command result = null;
		
		//Split command line on white spaces of any size
		List<String> commandArray = Arrays.asList(commandLine.trim().split("\\s+"));
		
		String name = commandArray.get(0);
		
		String movieShowId;
		
		switch (name) {
		case "print-movie-show":
			movieShowId = commandArray.get(1);
			result = new PrintMovieShowCommand(movieShowId, tws);
			break;
		case "print-all-movie-shows":
			result = new PrintAllMovieShowsCommand(null, tws);
			break;
		case "echo":
			result = new EchoCommand(commandArray.subList(1, commandArray.size()), tws);
			break;
		default:
			result = new InvalidCommand(commandArray.subList(1, commandArray.size()), tws);
			break;
		}
		
		return result;
	}
}
