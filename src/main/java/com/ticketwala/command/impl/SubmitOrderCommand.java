package com.ticketwala.command.impl;

import com.ticketwala.command.api.Command;
import com.ticketwala.command.api.Result;
import com.ticketwala.service.api.TicketWalaService;

public class SubmitOrderCommand extends Command {

	public SubmitOrderCommand(Object commandInput, TicketWalaService tws) {
		super(commandInput, tws);
	}

	@Override
	public Result execute() {
		String orderId = (String) this.commandInput;
		return this.ticketWalaService.submitOrder(orderId);
	}

}
