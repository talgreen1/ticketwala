package com.ticketwala.service.api;

import java.util.List;

import com.ticketwala.command.api.Result;
import com.ticketwala.model.MovieShow;
import com.ticketwala.model.Order;
import com.ticketwala.model.Seat;

public interface TicketWalaService {
	
	/**
	 * Get the list of all existing movie shows
	 * 
	 * @return A List of MovieShow
	 */
	List<MovieShow> getMovieShows();

	/**
	 * Find and return a single movie show
	 * 
	 * @return MovieShow
	 */
	MovieShow getMovieShow(String showId);
	
	/**
	 * Start an order for a Movie Show selected by the user.
	 * 
	 * @param showId
	 * @return Result
	 */
	Result createOrder(String showId);
	
	/**
	 * Add a seat ticket to an existing Order.
	 * 
	 * @param orderId
	 * @return Result
	 */
	Result addSeatTicket(String orderId, Seat seat);
	
	/**
	 * Submit the given Order.
	 * 
	 * @param orderId
	 * @return Result
	 */
	Result submitOrder(String orderId);
	
	
	/*************************************** Admin Operations ******************************************/
	/**
	 * Add a Movie Show to the System
	 * 
	 * @param movieShow
	 * @return Result
	 */
	Result addMovieShow(MovieShow movieShow);
	
	/**
	 * Delete a Movie Show from the System
	 * 
	 * @param showId
	 * @return Result
	 */
	Result deleteMovieShow(String showId);
	
	/**
	 * Returns a previously created Order by its id
	 * 
	 * @param orderId
	 * @return
	 */
	Order getOrder(String orderId);
}
