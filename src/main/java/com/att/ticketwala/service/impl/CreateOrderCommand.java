package com.att.ticketwala.service.impl;

import java.util.List;

import com.att.ticketwala.service.api.Command;
import com.att.ticketwala.service.api.Result;
import com.att.ticketwala.service.api.TicketWalaService;

public class CreateOrderCommand extends Command {

	public CreateOrderCommand(TicketWalaService service, List<String> args) {
		super(service, args);
	}

	@Override
	public Result execute() {
		//extract show name from args
		String showName = this.args.get(0);
		return this.ticketService.createOrder(showName);
	}

}
