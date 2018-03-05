package com.ticketwala.command.api;

import java.util.Arrays;
import java.util.List;

import com.ticketwala.command.impl.AddSeatCommand;
import com.ticketwala.command.impl.CreateOrderCommand;
import com.ticketwala.command.impl.EchoCommand;
import com.ticketwala.command.impl.InvalidCommand;
import com.ticketwala.command.impl.PrintAllMovieShowsCommand;
import com.ticketwala.command.impl.PrintMovieShowCommand;
import com.ticketwala.command.impl.PrintOrderStatusCommand;
import com.ticketwala.command.impl.SubmitOrderCommand;
import com.ticketwala.command.input.AddSeatCommandInput;
import com.ticketwala.command.input.CreateOrderCommandInput;
import com.ticketwala.service.api.TicketWalaService;

public class CommandFactory {
	public Command createCommand(TicketWalaService tws, String commandLine) {
		Command result = null;
		
		//Split command line on white spaces of any size
		List<String> commandArray = Arrays.asList(commandLine.trim().split("\\s+"));
		
		String name = commandArray.get(0);
		
		String movieShowId;
		String orderId;
		int seatRow;
		int seatNumber;
		
		switch (name) {
		case "create-order":
			movieShowId = commandArray.get(1);
			CreateOrderCommandInput coi = new CreateOrderCommandInput(movieShowId);
			result = new CreateOrderCommand(coi, tws);
			break;
		case "add-seat":
			orderId = commandArray.get(1);
			seatRow = Integer.parseInt(commandArray.get(2));
			seatNumber = Integer.parseInt(commandArray.get(3));
			AddSeatCommandInput asi = new AddSeatCommandInput(orderId, seatRow, seatNumber);
			result = new AddSeatCommand(asi, tws);
			break;
		case "submit-order":
			orderId = commandArray.get(1);
			result = new SubmitOrderCommand(orderId, tws);
			break;
		case "print-order":
			result = new PrintOrderStatusCommand(commandArray.get(1), tws);
			break;
		case "print-movie-show":
			movieShowId = commandArray.get(1);
			result = new PrintMovieShowCommand(movieShowId, tws);
			break;
		case "print-all-movie-shows":
			result = new PrintAllMovieShowsCommand(null, tws);
			break;
		case "echo":
		case "bye":
			result = new EchoCommand(commandArray.subList(1, commandArray.size()), tws);
			break;
		default:
			result = new InvalidCommand(commandArray.subList(1, commandArray.size()), tws);
			break;
		}
		
		return result;
	}
}
