package com.ticketwala.model;

public class ModelFactory {
	public CinemaHall createCinemaHall() {
		return new CinemaHall(5, 5, 30);
	}
}
