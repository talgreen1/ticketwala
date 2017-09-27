package com.att.ticketwala.service.impl;

import java.util.List;

import com.att.ticketwala.service.api.Command;
import com.att.ticketwala.service.api.MovieShow;
import com.att.ticketwala.service.api.Result;
import com.att.ticketwala.service.api.TicketWalaService;

public class PrintShowCommand extends Command {

	public PrintShowCommand(TicketWalaService service, List<String> args) {
		super(service, args);
	}

	@Override
	public Result execute() {
		MovieShow s = this.ticketService.getMovieShow(this.args.get(0));
		StringBuilder sb = new StringBuilder();
		sb.append(s.getCinemaHall().toString()).append('\n').append("Total Sold Seats: " + s.getSeatsSold() + "\n").append("Total Available Seats: " + s.getAvailableSeats() + "\n");
		return new Result(true, sb.toString());
	}

}
