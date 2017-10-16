package com.ticketwala.command.api;

import java.util.Arrays;
import java.util.List;

import com.ticketwala.command.impl.AddSeatCommand;
import com.ticketwala.command.impl.CreateMovieShowCommand;
import com.ticketwala.command.impl.CreateOrderCommand;
import com.ticketwala.command.impl.DeleteMovieShowCommand;
import com.ticketwala.command.impl.InvalidCommand;
import com.ticketwala.command.impl.ListMovieShowsCommand;
import com.ticketwala.command.impl.PrintOrderCommand;
import com.ticketwala.command.impl.PrintShowCommand;
import com.ticketwala.command.impl.SubmitOrderCommand;
import com.ticketwala.command.input.AddSeatCommandInput;
import com.ticketwala.command.input.CreateMovieShowInput;
import com.ticketwala.command.input.CreateOrderInput;
import com.ticketwala.service.api.TicketWalaService;

public class CommandFactory {
	public Command createCommand(TicketWalaService service, String commandLine) {
		Command result = null;
		
		//Split command line on spaces
		List<String> commandArray = Arrays.asList(commandLine.trim().split("\\s+"));
		
		String name = commandArray.get(0);
		
		switch (name) {
		case "create-order":
			CreateOrderInput coi = new CreateOrderInput();
			coi.setShowId(commandArray.get(1));
			result = new CreateOrderCommand(service, coi);
			break;
		case "add-seat":
			AddSeatCommandInput asci = new AddSeatCommandInput();
			asci.setOrderId(commandArray.get(1));
			asci.setRow(Integer.parseInt(commandArray.get(2)) - 1);
			asci.setSeat(Integer.parseInt(commandArray.get(3)) - 1);
			result = new AddSeatCommand(service, asci);
			break;
		case "submit-order":
			result = new SubmitOrderCommand(service, commandArray.get(1));
			break;
		case "movie-show-status":
			result = new PrintShowCommand(service, commandArray.get(1));
			break;
		case "order-details":
			result = new PrintOrderCommand(service, commandArray.get(1));
			break;
		case "add-movie-show":
			CreateMovieShowInput cmsi = new CreateMovieShowInput();
			cmsi.setName(commandArray.get(1));
			cmsi.setTime(commandArray.get(2));
			cmsi.setDuration(Integer.parseInt(commandArray.get(3)));			
			result = new CreateMovieShowCommand(service, cmsi);
			break;
		case "delete-movie-show":
			result = new DeleteMovieShowCommand(service, commandArray.get(1));
			break;
		case "list-movie-shows":
			result = new ListMovieShowsCommand(service, null);
			break;
		default:
			result = new InvalidCommand(service, null);
			break;
		}
		
		return result;
	}
}
