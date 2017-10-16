package com.ticketwala.command.input;

public class CreateOrderCommandInput {
	private String movieShowId;

	public CreateOrderCommandInput(String movieShowId) {
		this.movieShowId = movieShowId;
	}

	public String getMovieShowId() {
		return movieShowId;
	}
}
