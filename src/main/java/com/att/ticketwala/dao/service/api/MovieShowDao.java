package com.att.ticketwala.dao.service.api;

import com.att.ticketwala.service.api.MovieShow;
import com.att.ticketwala.service.api.Result;

public interface MovieShowDao {
	Result createMovieShow(MovieShow m);
	MovieShow fetchMovieShow(String id);
	Result deleteMovieShow(String id);
	Result updateMovieShow(String id, MovieShow m);
}
