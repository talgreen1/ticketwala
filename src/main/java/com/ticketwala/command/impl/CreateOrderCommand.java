package com.ticketwala.command.impl;

import com.ticketwala.command.api.Command;
import com.ticketwala.command.api.Result;
import com.ticketwala.command.input.CreateOrderCommandInput;
import com.ticketwala.service.api.TicketWalaService;

public class CreateOrderCommand extends Command {

	public CreateOrderCommand(Object commandInput, TicketWalaService tws) {
		super(commandInput, tws);
	}

	@Override
	public Result execute() {
		String movieShowId = ((CreateOrderCommandInput) this.commandInput).getMovieShowId();
		return this.ticketWalaService.createOrder(movieShowId);
	}

}
