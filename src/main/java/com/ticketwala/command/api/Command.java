package com.ticketwala.command.api;

import com.ticketwala.service.api.TicketWalaService;

public abstract class Command {
	
	protected String name;
	protected TicketWalaService ticketService;
	protected Object commandInput;
	
	public Command(TicketWalaService service, Object commandInput) {
		this.commandInput = commandInput;
		this.ticketService = service;
	}
	
	public abstract Result execute();
}
