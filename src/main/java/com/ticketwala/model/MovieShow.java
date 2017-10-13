package com.ticketwala.model;

import java.time.LocalDateTime;

public class MovieShow {
	
	/**
	 * These constants are here for simplicity.
	 * Normally they should be taken from database.
	 */
	private static final int CINEMA_SEATS_IN_A_ROW = 5;
	private static final int CINEMA_ROWS = 5;
	private static final double TICKET_PRICE = 30.0;
	
	private String id;
	private String movieName;
	private LocalDateTime time;
	private int duration;
	private CinemaHall cinemaHall;
	
	public MovieShow(String id, String movieName, LocalDateTime time, int duration) {
		this.id = id;
		this.movieName = movieName;
		this.duration = duration;
		this.time = time;
		this.cinemaHall = new CinemaHall(CINEMA_ROWS, CINEMA_SEATS_IN_A_ROW, TICKET_PRICE);
	}

	public String getId() {
		return id;
	}

	public String getMovieName() {
		return movieName;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public int getDuration() {
		return duration;
	}

	public CinemaHall getCinemaHall() {
		return cinemaHall;
	}
	
	@Override
	public String toString() {
		return "MovieShow [id=" + id + ", movieName=" + movieName + ", time=" + time + ", duration=" + duration + "]";
	}
}
