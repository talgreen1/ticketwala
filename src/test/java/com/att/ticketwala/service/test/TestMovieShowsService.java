package com.att.ticketwala.service.test;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.att.ticketwala.admin.service.api.MovieShowsService;
import com.att.ticketwala.admin.service.impl.MovieShowsServiceImpl;
import com.att.ticketwala.service.api.MovieShow;
import com.att.ticketwala.service.api.Result;

public class TestMovieShowsService {
	private MovieShowsService movieShowService = null;
	
	@Before
	public void setUp() throws Exception {
		this.movieShowService = new MovieShowsServiceImpl();
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

//	@Test
//	public void testGetMovieShow() {
//		
//	}
	
	public static void main(String[] args) {
		LocalDateTime ldt = LocalDateTime.of(2016, 10, 1, 20, 30);
		System.out.println(ldt);
	}
}
