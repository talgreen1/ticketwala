package com.ticketwala.command.impl;

import java.util.List;

import com.ticketwala.command.api.Command;
import com.ticketwala.command.api.Result;

public class EchoCommand extends Command {

	public EchoCommand(Object commandInput) {
		super(commandInput);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Result execute() {
		((List<String>)this.commandInput).stream().forEach((s) -> System.out.print(s + " "));
		System.out.println();
		return new Result(true, "OK");
	}

}
