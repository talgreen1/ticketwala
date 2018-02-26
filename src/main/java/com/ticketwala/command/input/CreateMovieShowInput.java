package com.ticketwala.command.input;

public class CreateMovieShowInput {
	private String name;
	private int duration;
	private String time;
	
	public CreateMovieShowInput() {
		//Empty
	}

	public CreateMovieShowInput(String name, int duration, String time) {
		super();
		this.name = name;
		this.duration = duration;
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "MovieShowForm [name=" + name + ", duration=" + duration + ", time=" + time + "]";
	}
}
