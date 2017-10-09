package com.ticketwala.command.impl;

import com.ticketwala.command.api.Command;
import com.ticketwala.command.api.Result;

public class InvalidCommand extends Command {

	public InvalidCommand(Object commandInput) {
		super(commandInput);
	}

	@Override
	public Result execute() {
		return new Result(false, "Invalid Command");
	}

}
