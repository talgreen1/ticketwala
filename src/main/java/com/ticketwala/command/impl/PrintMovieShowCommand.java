package com.ticketwala.command.impl;

import com.ticketwala.command.api.Command;
import com.ticketwala.command.api.Result;
import com.ticketwala.model.MovieShow;
import com.ticketwala.service.api.TicketWalaService;

public class PrintMovieShowCommand extends Command {

	public PrintMovieShowCommand(Object commandInput, TicketWalaService tws) {
		super(commandInput, tws);
	}

	@Override
	public Result execute() {
		String movieShowId = (String) this.commandInput;
		MovieShow ms = this.ticketWalaService.getMovieShow(movieShowId);
		if (ms != null)	{
			return new Result(true, ms.toString() + '\n' + ms.getCinemaHall());
		} else {
			return new Result(false, "Movie Show " + movieShowId + "not Found!");
		}
	}

}
