package com.ticketwala.command.api;

public abstract class Command {
	
	protected String name;
	protected Object commandInput;
	
	public Command(Object commandInput) {
		this.commandInput = commandInput;
	}
	
	public abstract Result execute();
}
