package com.ticketwala.dao.api;

import java.util.HashMap;

import com.ticketwala.command.api.Result;
import com.ticketwala.model.MovieShow;
import com.ticketwala.model.Order;

public interface MovieShowDao {
	Result createMovieShow(MovieShow m);
	MovieShow fetchMovieShow(String id);
	Result deleteMovieShow(String id);
	Result updateMovieShow(String id, MovieShow m);
	HashMap<String, MovieShow> fetchAll();
	void deleteAll();
	void commitOrder(Order order);
	
}
