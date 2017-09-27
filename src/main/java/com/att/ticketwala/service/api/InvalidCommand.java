package com.att.ticketwala.service.api;

import java.util.List;

public class InvalidCommand extends Command {

	public InvalidCommand(TicketWalaService service, List<String> args) {
		super(service, args);
	}

	@Override
	public Result execute() {
		return new Result(false, "Invalid Command");
	}

}
