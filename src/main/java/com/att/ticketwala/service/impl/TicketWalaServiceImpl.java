package com.att.ticketwala.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.att.ticketwala.service.api.MovieShow;
import com.att.ticketwala.service.api.Order;
import com.att.ticketwala.service.api.Report;
import com.att.ticketwala.service.api.Result;
import com.att.ticketwala.service.api.Seat;
import com.att.ticketwala.service.api.TicketWalaService;

public class TicketWalaServiceImpl implements TicketWalaService {

	private Map<String, MovieShow> shows = new HashMap<String, MovieShow>();
	private Map<String, Order> orders = new HashMap<String, Order>();
	
	public TicketWalaServiceImpl() {
		//this.shows.put("show1", new MovieShow("show1"));
	}

	@Override
	public Report getSalesReport() {
		// TODO Auto-generated method stub
		return null;
	}
 
	@Override
	public Result createOrder(String showName) {
		String orderId = UUID.randomUUID().toString().substring(0, 5);
		this.orders.put(orderId, new Order(orderId, this.shows.get(showName)));
		return new Result(true, "New order created " + orderId);
	}

	@Override
	public Order getOrder(String orderId) {
		return this.orders.get(orderId);
	}

	@Override
	public MovieShow getMovieShow(String showName) {
		return this.shows.get(showName);
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
		MovieShow movieShow = this.shows.get(order.getMovieShow().getName());
		
		movieShow.commitOrder(order);
		res = new Result(true, "Order submitted successfully. Tickets ordered.");
		return res;
	}

	@Override
	public String[] getAvailableShows() {
		return this.shows.keySet().toArray(new String[shows.size()]);
	}

}
