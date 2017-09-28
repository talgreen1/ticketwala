package com.att.ticketwala.service.impl;

import java.util.List;

import com.att.ticketwala.service.api.Command;
import com.att.ticketwala.service.api.Result;
import com.att.ticketwala.service.api.TicketWalaService;

public class ListMovieShowsCommand extends Command {

	public ListMovieShowsCommand(TicketWalaService service, List<String> args) {
		super(service, args);
	}

	@Override
	public Result execute() {
		return new Result(true, this.ticketService.getMovieShows().toString());
	}

}
