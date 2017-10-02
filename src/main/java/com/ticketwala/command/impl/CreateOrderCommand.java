package com.ticketwala.command.impl;

import com.ticketwala.command.api.Command;
import com.ticketwala.command.api.Result;
import com.ticketwala.command.input.CreateOrderInput;
import com.ticketwala.service.api.TicketWalaService;

public class CreateOrderCommand extends Command {

	public CreateOrderCommand(TicketWalaService service, Object input) {
		super(service, input);
	}
	
	@Override
	public Result execute() {
		CreateOrderInput input = (CreateOrderInput) this.commandInput;
		String showId = input.getShowId();
		return this.ticketService.createOrder(showId);
	}

}
