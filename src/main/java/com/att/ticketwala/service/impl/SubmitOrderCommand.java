package com.att.ticketwala.service.impl;

import java.util.List;

import com.att.ticketwala.service.api.Command;
import com.att.ticketwala.service.api.Order;
import com.att.ticketwala.service.api.Result;
import com.att.ticketwala.service.api.TicketWalaService;

public class SubmitOrderCommand extends Command {

	public SubmitOrderCommand(TicketWalaService service, List<String> args) {
		super(service, args);
	}

	@Override
	public Result execute() {
		String orderId = this.args.get(0);
		Order order = this.ticketService.getOrder(orderId);
		return this.ticketService.submitOrder(order);
	}

}
