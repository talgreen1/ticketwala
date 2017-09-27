package com.att.ticketwala.service.api;

import java.util.Arrays;
import java.util.List;

import com.att.ticketwala.service.impl.AddSeatCommand;
import com.att.ticketwala.service.impl.CreateOrderCommand;
import com.att.ticketwala.service.impl.PrintOrderCommand;
import com.att.ticketwala.service.impl.PrintShowCommand;
import com.att.ticketwala.service.impl.SubmitOrderCommand;

public class CommandFactory {
	public static Command createCommand(TicketWalaService service, String commandLine) {
		Command result = null;
		
		//Split command line on spaces
		List<String> commandArray = Arrays.asList(commandLine.trim().split("\\s+"));
		
		String name = commandArray.get(0);
		
		switch (name) {
		case "create-order":
			result = new CreateOrderCommand(service, commandArray.subList(1, commandArray.size()));
			break;
		case "add-seat":
			result = new AddSeatCommand(service, commandArray.subList(1, commandArray.size()));
			break;
		case "submit-order":
			result = new SubmitOrderCommand(service, commandArray.subList(1, commandArray.size()));
			break;
		case "movie-show-status":
			result = new PrintShowCommand(service, commandArray.subList(1, commandArray.size()));
			break;
		case "order-details":
			result = new PrintOrderCommand(service, commandArray.subList(1, commandArray.size()));
			break;
		default:
			result = new InvalidCommand(service, commandArray.subList(1, commandArray.size()));
			break;
		}
		
		return result;
	}
}
