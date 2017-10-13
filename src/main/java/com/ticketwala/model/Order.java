package com.ticketwala.model;

import java.util.LinkedList;
import java.util.List;

public class Order {
	private String id;
	private MovieShow movieShow;
	private List<Seat> seats;
	
	public Order(String id, MovieShow movieShow) {
		this.id = id;
		this.movieShow = movieShow;
		this.seats = new LinkedList<Seat>();
	}

	public String getId() {
		return id;
	}

	public MovieShow getMovieShow() {
		return movieShow;
	}

	public List<Seat> getSeats() {
		return seats;
	}
	
	public boolean addSeat(Seat seatToOrder) {
		
		//Check if seat is taken in the cinema hall
		if (movieShow.getCinemaHall().getSeat(seatToOrder.getRow(), seatToOrder.getSeatNumber()).isSold()) {
			return false;
		}
		
		//Check if seat already is in order
		else if (seats.contains(seatToOrder)) {
			return false;
		} else {
			return this.seats.add(seatToOrder);
		}
		
	}
	
	public double getTotalCost() {
		double result = 0;
		for (Seat seat : seats) {
			result += seat.getPrice();
		}
		return result;
	}
	
}
