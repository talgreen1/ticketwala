package com.ticketwala.command.impl;

import com.ticketwala.command.api.Command;
import com.ticketwala.command.api.Result;
import com.ticketwala.model.MovieShow;
import com.ticketwala.service.api.TicketWalaService;

public class PrintShowCommand extends Command {

	public PrintShowCommand(TicketWalaService service, Object input) {
		super(service, input);
	}
	
	@Override
	public Result execute() {
		MovieShow s = this.ticketService.getMovieShow((String) this.commandInput);
		StringBuilder sb = new StringBuilder();
		sb.append(s.getCinemaHall().toString()).append('\n').append("Total Sold Seats: " + s.getSeatsSold() + "\n").append("Total Available Seats: " + s.getAvailableSeats() + "\n");
		return new Result(true, sb.toString());
	}

}
