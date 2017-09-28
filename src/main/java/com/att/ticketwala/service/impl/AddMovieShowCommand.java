package com.att.ticketwala.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.att.ticketwala.service.api.Command;
import com.att.ticketwala.service.api.MovieShow;
import com.att.ticketwala.service.api.Result;
import com.att.ticketwala.service.api.TicketWalaService;

public class AddMovieShowCommand extends Command {

	public AddMovieShowCommand(TicketWalaService service, List<String> args) {
		super(service, args);
	}

	@Override
	public Result execute() {
		String name = this.args.get(0);
		LocalDateTime time = LocalDateTime.parse(this.args.get(1));
		int duration = Integer.parseInt(this.args.get(2));
		MovieShow movieShow = new MovieShow(UUID.randomUUID().toString().substring(0,5), name, time, duration);
		return this.ticketService.addMovieShow(movieShow );
	}

}
