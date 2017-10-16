package com.ticketwala.dao.test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ticketwala.command.api.Result;
import com.ticketwala.dao.api.DataAccessService;
import com.ticketwala.dao.impl.DataAccessServiceImpl;
import com.ticketwala.model.MovieShow;

public class TestDataAccessServiceService {
	private DataAccessService dataAccessService = null;
	
	public TestDataAccessServiceService() {
		this.dataAccessService = new DataAccessServiceImpl();
		this.dataAccessService.deleteAllMovieShows();
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
		Result res = this.dataAccessService.createMovieShow(movieShow);
		
		System.out.println(res);
		Assert.assertTrue(res != null && res.isSuccess());
		
		movieShow = this.dataAccessService.findMovieShow(id);
		Assert.assertTrue(movieShow != null);
		Assert.assertTrue(movieShow.getId().equals(id));
	}

	@Test
	public void testGetMovieShows() {
		LocalDateTime ldt = LocalDateTime.of(2016, 10, 1, 20, 30);
		int duration = 90;
		
		HashMap<String, MovieShow> movieShows = this.dataAccessService.getAllMovieShows();
		int sizeBefore = movieShows.size();
		
		MovieShow movieShow = new MovieShow("1", "movie1", ldt, duration);
		Result res = this.dataAccessService.createMovieShow(movieShow);
		Assert.assertTrue(res != null && res.isSuccess());
		MovieShow movieShow1 = new MovieShow("2", "movie2", ldt, duration);
		res = this.dataAccessService.createMovieShow(movieShow1);
		Assert.assertTrue(res != null && res.isSuccess());
		
		movieShows = this.dataAccessService.getAllMovieShows();
		
		Assert.assertTrue(movieShows.size() == sizeBefore + 2);
	}
	

}
