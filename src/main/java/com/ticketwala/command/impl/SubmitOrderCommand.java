package com.ticketwala.command.impl;

import com.ticketwala.command.api.Command;
import com.ticketwala.command.api.Result;
import com.ticketwala.model.Order;
import com.ticketwala.service.api.TicketWalaService;

public class SubmitOrderCommand extends Command {

	public SubmitOrderCommand(TicketWalaService service, Object input) {
		super(service, input);
	}
	
	@Override
	public Result execute() {
		String orderId = (String) this.commandInput;
		Order order = this.ticketService.getOrder(orderId);
		return this.ticketService.submitOrder(order);
	}

}
