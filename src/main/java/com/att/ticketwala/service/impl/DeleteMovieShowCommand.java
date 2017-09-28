package com.att.ticketwala.service.impl;

import java.util.List;

import com.att.ticketwala.service.api.Command;
import com.att.ticketwala.service.api.Result;
import com.att.ticketwala.service.api.TicketWalaService;

public class DeleteMovieShowCommand extends Command {

	public DeleteMovieShowCommand(TicketWalaService service, List<String> args) {
		super(service, args);
	}

	@Override
	public Result execute() {
		Result res = this.ticketService.deleteMovieShow(this.args.get(0));
		return res;
	}

}
