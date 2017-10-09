package com.ticketwala.command.api;

import java.util.Arrays;
import java.util.List;

import com.ticketwala.command.impl.EchoCommand;
import com.ticketwala.command.impl.InvalidCommand;

public class CommandFactory {
	public Command createCommand(String commandLine) {
		Command result = null;
		
		//Split command line on white spaces of any size
		List<String> commandArray = Arrays.asList(commandLine.trim().split("\\s+"));
		
		String name = commandArray.get(0);
		
		switch (name) {
		case "echo":
			result = new EchoCommand(commandArray.subList(1, commandArray.size())); //Send only arguments to command
			break;
		default:
			result = new InvalidCommand(commandArray.subList(1, commandArray.size())); //Send only arguments to command
			break;
		}
		
		return result;
	}
}
