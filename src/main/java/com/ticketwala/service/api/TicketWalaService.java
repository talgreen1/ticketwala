package com.ticketwala.service.api;

import java.util.HashMap;

import com.ticketwala.command.api.Result;
import com.ticketwala.model.MovieShow;
import com.ticketwala.model.Order;
import com.ticketwala.model.Seat;

public interface TicketWalaService {

	//Order APIs
	Result createOrder(String showId);
	Order getOrder(String orderId);
	Result addSeat(String orderId, Seat seat);
	Result submitOrder(Order order);
	
	//Shows APIs
	Result addMovieShow(MovieShow movieShow);
	MovieShow getMovieShow(String showId);
	HashMap<String, MovieShow> getMovieShows();
	void deleteAllMovieShows();
	Result deleteMovieShow(String showId);
}
