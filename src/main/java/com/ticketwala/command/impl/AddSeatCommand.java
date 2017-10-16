package com.ticketwala.command.impl;

import com.ticketwala.command.api.Command;
import com.ticketwala.command.api.Result;
import com.ticketwala.command.input.AddSeatCommandInput;
import com.ticketwala.service.api.TicketWalaService;

public class AddSeatCommand extends Command {

	public AddSeatCommand(Object commandInput, TicketWalaService tws) {
		super(commandInput, tws);
	}

	@Override
	public Result execute() {
		AddSeatCommandInput asi = (AddSeatCommandInput) this.commandInput;
		return this.ticketWalaService.addSeatTicket(asi.getOrderId(), asi.getRow(), asi.getSeatNumber());
	}

}
