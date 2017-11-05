package com.ticketwala.command.impl;

import com.ticketwala.command.api.Command;
import com.ticketwala.command.api.Result;
import com.ticketwala.service.api.TicketWalaService;

public class DeleteAllMovieShowsCommand extends Command {

	public DeleteAllMovieShowsCommand(Object commandInput, TicketWalaService tws) {
		super(commandInput, tws);
	}

	@Override
	public Result execute() {
		return this.ticketWalaService.deleteAllMovieShows();
	}

}
