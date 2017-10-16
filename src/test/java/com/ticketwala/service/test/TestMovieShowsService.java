package com.ticketwala.service.test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ticketwala.command.api.Result;
import com.ticketwala.model.MovieShow;
import com.ticketwala.service.api.TicketWalaService;
import com.ticketwala.service.impl.TicketWalaServiceImpl;

public class TestMovieShowsService {
	private TicketWalaService movieShowService = null;
	
	public TestMovieShowsService() {
		this.movieShowService = new TicketWalaServiceImpl();
		this.movieShowService.deleteAllMovieShows();
	}
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddMovieShow() {
		LocalDateTime ldt = LocalDateTime.of(2016, 10, 1, 20, 30);
		int duration = 90;
		
		String id = UUID.randomUUID().toString();
		MovieShow movieShow = new MovieShow(id, "Big Lebowsky", ldt, duration);
		Result res = this.movieShowService.addMovieShow(movieShow);
		
		System.out.println(res);
		Assert.assertTrue(res != null && res.isSuccess());
		
		movieShow = this.movieShowService.getMovieShow(id);
		Assert.assertTrue(movieShow != null);
		Assert.assertTrue(movieShow.getId().equals(id));
	}

	@Test
	public void testGetMovieShows() {
		LocalDateTime ldt = LocalDateTime.of(2016, 10, 1, 20, 30);
		int duration = 90;
		
		HashMap<String, MovieShow> movieShows = this.movieShowService.getMovieShows();
		int sizeBefore = movieShows.size();
		
		MovieShow movieShow = new MovieShow("1", "movie1", ldt, duration);
		Result res = this.movieShowService.addMovieShow(movieShow);
		Assert.assertTrue(res != null && res.isSuccess());
		MovieShow movieShow1 = new MovieShow("2", "movie2", ldt, duration);
		res = this.movieShowService.addMovieShow(movieShow1);
		Assert.assertTrue(res != null && res.isSuccess());
		
		movieShows = this.movieShowService.getMovieShows();
		
		Assert.assertTrue(movieShows.size() == sizeBefore + 2);
	}
	

}
