package com.att.ticketwala.admin.service.impl;

import com.att.ticketwala.admin.service.api.MovieShowsService;
import com.att.ticketwala.dao.service.api.MovieShowDao;
import com.att.ticketwala.dao.service.impl.MovieShowDaoImpl;
import com.att.ticketwala.service.api.MovieShow;
import com.att.ticketwala.service.api.Result;

public class MovieShowsServiceImpl implements MovieShowsService {
	
	private MovieShowDao dao = null;
	
	public MovieShowsServiceImpl() {
		this.dao = new MovieShowDaoImpl();
	}
	
	@Override
	public Result addMovieShow(MovieShow movieShow) {
//		Result res = new Result(false, "Movie with ID " + id + " does not exist!");
		return this.dao.createMovieShow(movieShow);
	}

	@Override
	public MovieShow getMovieShow(String id) {
		return this.dao.fetchMovieShow(id);
	}

}
