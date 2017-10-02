package com.ticketwala.command.impl;

import com.ticketwala.command.api.Command;
import com.ticketwala.command.api.Result;
import com.ticketwala.service.api.TicketWalaService;

public class InvalidCommand extends Command {

	public InvalidCommand(TicketWalaService service, Object commandInput) {
		super(service, commandInput);
	}

	@Override
	public Result execute() {
		return new Result(false, "Invalid Command");
	}

}
