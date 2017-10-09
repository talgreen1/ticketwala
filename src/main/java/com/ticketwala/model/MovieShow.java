package com.ticketwala.model;

import java.time.LocalDateTime;

public class MovieShow {
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
		this.cinemaHall = new ModelFactory().createCinemaHall();
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
