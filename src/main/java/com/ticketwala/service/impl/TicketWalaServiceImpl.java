package com.ticketwala.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.ticketwala.command.api.Result;
import com.ticketwala.dao.api.DataAccessService;
import com.ticketwala.dao.impl.DataAccessServiceImpl;
import com.ticketwala.model.MovieShow;
import com.ticketwala.model.Order;
import com.ticketwala.model.Seat;
import com.ticketwala.service.api.TicketWalaService;

public class TicketWalaServiceImpl implements TicketWalaService {
	
	private DataAccessService das = null;
	private HashMap<String, Order> orders = null;
	
	public TicketWalaServiceImpl() {
		this.das = new DataAccessServiceImpl();
		this.orders = new HashMap<String, Order>();
	}
	
	@Override
	public List<MovieShow> getMovieShows() {
		return new ArrayList<MovieShow>(this.das.getAllMovieShows().values());
	}

	@Override
	public MovieShow getMovieShow(String showId) {
		return this.das.findMovieShow(showId);
	}

	@Override
	public Result createOrder(String showId) {
		Result res = new Result(false, "Failed to create order. Movie show not found!");
		MovieShow show = this.das.findMovieShow(showId);
		if (show != null) {
			String newOrderId = generateId();
			this.orders.put(newOrderId, new Order(newOrderId, show));
			res = new Result(true, newOrderId);
		}
		return res;
	}

	@Override
	public Result addSeatTicket(String orderId, Seat seat) {
		Result res = new Result(false, "Failed to add seat " + seat + " to order " + orderId + ". Seat already ordered?");
		Order order = this.orders.get(orderId);
		boolean success = order.addSeat(seat);
		if (success) {
			res = new Result(true, "Added seat " + seat + " to order " + orderId);
		}
		return res;
	}

	@Override
	public Result submitOrder(String orderId) {
		return this.das.commitOrder(this.orders.get(orderId));
	}


	@Override
	public Result addMovieShow(MovieShow movieShow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result deleteMovieShow(String showId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getOrder(String orderId) {
		return this.orders.get(orderId);
	}

	public String generateId() {
		return UUID.randomUUID().toString().substring(0, 5);
	}
}
