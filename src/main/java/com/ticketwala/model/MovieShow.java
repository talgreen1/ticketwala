package com.ticketwala.model;

import java.time.LocalDateTime;
import java.util.List;

import com.ticketwala.service.api.Configuration;

public class MovieShow {
	private String name;
	private CinemaHall cinemaHall;
	private int duration;
	private String id;
	
	private LocalDateTime time;
	
	public MovieShow(String id, String name, LocalDateTime time, int duration) {
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.time = time;
		this.cinemaHall = new CinemaHall(Configuration.CINEMA_ROWS, Configuration.CINEMA_SEATS_IN_ROW, Configuration.SEAT_PRICE);
	}
	
	public CinemaHall getCinemaHall() {
		return cinemaHall;
	}

	public String getName() {
		return name;
	}
	
	public int getSeatsSold() {
		return this.cinemaHall.getSize() - this.cinemaHall.countAvailableSeats();
	}

	public double getTicketPrice() {
		return Configuration.SEAT_PRICE;
	}

	public void commitOrder(Order order) {
		List<Seat> seats = order.getSeats();
		for (Seat seat : seats) {
			this.getCinemaHall().getSeatsArray()[seat.getRow()][seat.getSeat()].setSold(true);
		}
	}
	
	public int getAvailableSeats() {
		return this.getCinemaHall().countAvailableSeats();
	}
	
	public int getDuration() {
		return duration;
	}
	
	public LocalDateTime getTime() {
		return time;
	}
	
	@Override
	public String toString() {
		return String.format("{ name : %s, date : %s, duration : %d }", this.name, this.time.toString(), this.duration);
	}

	public String getId() {
		return this.id;
	}
}
