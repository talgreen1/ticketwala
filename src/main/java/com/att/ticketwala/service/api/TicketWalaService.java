package com.att.ticketwala.service.api;

public interface TicketWalaService {
	Report getSalesReport();
	Result createOrder(String showName);
	Order getOrder(String orderId);
	MovieShow getMovieShow(String showName);
	Result addSeat(String orderId, Seat seat);
	Result submitOrder(Order order);
	String[] getAvailableShows();
}
