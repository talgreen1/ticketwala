package com.att.ticketwala.admin.service.api;

import com.att.ticketwala.service.api.MovieShow;
import com.att.ticketwala.service.api.Result;

public interface MovieShowsService {

	Result addMovieShow(MovieShow movieShow);

	MovieShow getMovieShow(String id);

}
