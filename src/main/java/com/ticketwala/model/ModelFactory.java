package com.ticketwala.model;

public class ModelFactory {
	
	public static final double TICKET_PRICE = 30.0;
	
	public CinemaHall createCinemaHall() {
		return new CinemaHall(5, 5, TICKET_PRICE);
	}

	public Seat createSeat(int row, int seat) {
		return new Seat(row, seat, TICKET_PRICE);
	}
}
