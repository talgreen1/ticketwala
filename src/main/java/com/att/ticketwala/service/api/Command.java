package com.att.ticketwala.service.api;

import java.util.List;

public abstract class Command {
	
	protected String name;
	protected List<String> args;
	protected TicketWalaService ticketService;
	
	public Command(TicketWalaService service, List<String> args) {
		this.args = args;
		this.ticketService = service;
	}
	
	public abstract Result execute();
}
