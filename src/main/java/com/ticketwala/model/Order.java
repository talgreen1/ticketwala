package com.ticketwala.model;

import java.util.LinkedList;
import java.util.List;

public class Order {
	private String id;
	private MovieShow show;
	private List<Seat> seats = new LinkedList<Seat>();
	
	public Order(String id, MovieShow show) {
		this.show = show;
	}

	public String getId() {
		return id;
	}
	public MovieShow getMovieShow() {
		return show;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void addSeat(Seat seat) {
		this.seats.add(seat);
	}
}
