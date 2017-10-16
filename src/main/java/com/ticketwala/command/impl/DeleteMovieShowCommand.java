package com.ticketwala.command.impl;

import com.ticketwala.command.api.Command;
import com.ticketwala.command.api.Result;
import com.ticketwala.service.api.TicketWalaService;

public class DeleteMovieShowCommand extends Command {

	public DeleteMovieShowCommand(TicketWalaService service, Object input) {
		super(service, input);
	}
	
	@Override
	public Result execute() {
		Result res = this.ticketService.deleteMovieShow((String) this.commandInput);
		return res;
	}

}
