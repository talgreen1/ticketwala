package com.ticketwala.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ticketwala.command.api.Result;
import com.ticketwala.dao.api.MovieShowDao;
import com.ticketwala.dao.impl.MovieShowDaoImpl;
import com.ticketwala.model.MovieShow;
import com.ticketwala.model.Order;
import com.ticketwala.model.Seat;
import com.ticketwala.service.api.TicketWalaService;

@Service
public class TicketWalaServiceImpl implements TicketWalaService {

	private Map<String, Order> orders = new HashMap<String, Order>();
	private MovieShowDao dao = null;
	
	public TicketWalaServiceImpl() {
		this.dao = new MovieShowDaoImpl();
	}

	@Override
	public Result createOrder(String showId) {
		String orderId = UUID.randomUUID().toString().substring(0, 5);
		this.orders.put(orderId, new Order(orderId, this.dao.fetchMovieShow(showId)));
		return new Result(true, "New order created " + orderId);
	}

	@Override
	public Order getOrder(String orderId) {
		return this.orders.get(orderId);
	}

	@Override
	public Result addSeat(String orderId, Seat seat) {
		Result res = null;
		Order order = this.orders.get(orderId);
		if (order != null) {
			order.addSeat(seat);
			res = new Result(true, "Order " + orderId + " updated with added seat " + seat.toString2());
		} else {
			res = new Result(false, "Couldnt find order with ID " + orderId);
		}
		return res;
	}

	@Override
	public Result submitOrder(Order order) {
		Result res = null;
		this.dao.commitOrder(order);
		res = new Result(true, "Order submitted successfully. Tickets ordered.");
		return res;
	}

	@Override
	public Result addMovieShow(MovieShow movieShow) {
		return this.dao.createMovieShow(movieShow);
	}

	@Override
	public MovieShow getMovieShow(String id) {
		return this.dao.fetchMovieShow(id);
	}

	@Override
	public HashMap<String, MovieShow> getMovieShows() {
		return this.dao.fetchAll();
	}

	@Override
	public void deleteAllMovieShows() {
		this.dao.deleteAll();
	}

	@Override
	public Result deleteMovieShow(String showId) {
		return this.dao.deleteMovieShow(showId);
	}
}
