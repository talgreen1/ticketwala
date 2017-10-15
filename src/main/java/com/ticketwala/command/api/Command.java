package com.ticketwala.command.api;

import com.ticketwala.service.api.TicketWalaService;

public abstract class Command {
	
	protected String name;
	protected Object commandInput;
	protected TicketWalaService ticketWalaService;
	
	public Command(Object commandInput, TicketWalaService tws) {
		this.commandInput = commandInput;
		this.ticketWalaService = tws;
	}
	
	public abstract Result execute();
}
