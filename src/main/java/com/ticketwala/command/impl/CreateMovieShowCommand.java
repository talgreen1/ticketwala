package com.ticketwala.command.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ticketwala.command.api.Command;
import com.ticketwala.command.api.Result;
import com.ticketwala.command.input.CreateMovieShowInput;
import com.ticketwala.model.MovieShow;
import com.ticketwala.service.api.TicketWalaService;

public class CreateMovieShowCommand extends Command {

	public CreateMovieShowCommand(Object commandInput, TicketWalaService tws) {
		super(commandInput, tws);
	}

	@Override
	public Result execute() {
		CreateMovieShowInput movieShowInput = (CreateMovieShowInput) this.commandInput;
		String name = movieShowInput.getName();
		LocalDateTime time = LocalDateTime.parse(movieShowInput.getTime());
		int duration = movieShowInput.getDuration();
		MovieShow movieShow = new MovieShow(UUID.randomUUID().toString().substring(0, 5), name, time, duration);
		return this.ticketWalaService.addMovieShow(movieShow );
	}

}
