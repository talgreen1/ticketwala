package com.ticketwala.command.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ticketwala.command.api.Command;
import com.ticketwala.command.api.Result;
import com.ticketwala.command.input.CreateMovieShowInput;
import com.ticketwala.model.MovieShow;
import com.ticketwala.service.api.TicketWalaService;

public class CreateMovieShowCommand extends Command {

	public CreateMovieShowCommand(TicketWalaService service, Object input) {
		super(service, input);
	}
	
	@Override
	public Result execute() {
		CreateMovieShowInput input = (CreateMovieShowInput) this.commandInput;
		String name = input.getName();
		LocalDateTime time = LocalDateTime.parse(input.getTime());
		int duration = input.getDuration();
		MovieShow movieShow = new MovieShow(UUID.randomUUID().toString().substring(0,5), name, time, duration);
		return this.ticketService.addMovieShow(movieShow );
	}
	

}
