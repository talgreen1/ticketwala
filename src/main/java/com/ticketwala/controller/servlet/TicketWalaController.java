package com.ticketwala.controller.servlet;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ticketwala.command.api.Result;
import com.ticketwala.command.impl.CreateMovieShowCommand;
import com.ticketwala.command.input.CreateMovieShowInput;
import com.ticketwala.model.MovieShow;
import com.ticketwala.service.api.TicketWalaService;

@RestController
public class TicketWalaController {
	
	@Autowired
	TicketWalaService ticketWalaService;
	
	@RequestMapping(value = "/movie_shows", method = RequestMethod.GET)
	public HashMap<String, MovieShow> list() {
		return this.ticketWalaService.getMovieShows();
	}	

	@RequestMapping(value = "/movie_show/{id}", method = RequestMethod.GET)
	public MovieShow getMovieShow(@PathVariable("id") String id) {
		return this.ticketWalaService.getMovieShow(id);
	}
	
	@RequestMapping(value = "/movie_show", method = RequestMethod.POST)
	public Result createMovieShow(@RequestBody CreateMovieShowInput movieShowInput) {
		CreateMovieShowCommand cmd = new CreateMovieShowCommand(this.ticketWalaService, movieShowInput);
		return cmd.execute();
	}
	
}

