package com.ticketwala.controller.servlet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ticketwala.command.api.Result;
import com.ticketwala.command.impl.CreateMovieShowCommand;
import com.ticketwala.command.impl.DeleteAllMovieShowsCommand;
import com.ticketwala.command.input.CreateMovieShowInput;
import com.ticketwala.model.MovieShow;
import com.ticketwala.service.api.TicketWalaService;


@RestController
@RequestMapping("movie_shows")
public class TicketWalaController {
	
	@Autowired
	TicketWalaService ticketWalaService;
	
	@GetMapping
	public List<MovieShow> getMovieAllShows() {
		return this.ticketWalaService.getMovieShows();
	}	

	@GetMapping("{id}")
	public ResponseEntity<MovieShow> getMovieShow(@PathVariable("id") String id) {
		MovieShow movieShow = this.ticketWalaService.getMovieShow(id);
		HttpStatus s = movieShow == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
		return new ResponseEntity<MovieShow>(movieShow, s);
	}
	
	//@RequestMapping(value = "/movie_show", method = RequestMethod.POST)
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createMovieShow(@RequestBody CreateMovieShowInput movieShowInput) {
		CreateMovieShowCommand cmd = new CreateMovieShowCommand(movieShowInput, this.ticketWalaService);
		cmd.execute();
	}

	@DeleteMapping
	public ResponseEntity<Result> deleteAllMovieShows(){
		DeleteAllMovieShowsCommand cmd = new DeleteAllMovieShowsCommand(null, this.ticketWalaService);
		return new ResponseEntity<Result>(cmd.execute(), HttpStatus.OK);
	}
}

