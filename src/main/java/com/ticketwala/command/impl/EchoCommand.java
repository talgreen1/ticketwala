package com.ticketwala.command.impl;

import java.util.List;

import com.ticketwala.command.api.Command;
import com.ticketwala.command.api.Result;
import com.ticketwala.service.api.TicketWalaService;

public class EchoCommand extends Command {

	public EchoCommand(Object commandInput, TicketWalaService tws) {
		super(commandInput, tws);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Result execute() {
		((List<String>)this.commandInput).stream().forEach((s) -> System.out.print(s + " "));
		System.out.println();
		return new Result(true, "OK");
	}

}
