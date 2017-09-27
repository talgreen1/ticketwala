package com.att.ticketwala.service.api;

public class CinemaHall {
	private int id;
	private Seat[][] seatsArray;
	private double seatPrice;
	private int size;

	public CinemaHall(int rows, int seats, double seatPrice) {
		this.seatsArray = new Seat[rows][seats];
		this.seatPrice = seatPrice;
		this.size = rows*seats;
		for (int i = 0; i < seatsArray.length; i++) {
			for (int j = 0; j < seatsArray[i].length; j++) {
				seatsArray[i][j] = new Seat(i, j, seatPrice);
			}
		}
	}
	
	public int getId() {
		return id;
	}

	public Seat[][] getSeatsArray() {
		return seatsArray;
	}

	public double getSeatPrice() {
		return seatPrice;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (Seat[] seats : seatsArray) {
			for (Seat seat : seats) {
				sb.append(seat.toString());
			}
			sb.append('\n');
		}

		return sb.toString();
	}	
	
	public Seat[][] getSeats() {
		return seatsArray;
	}

	public int getSize() {
		return this.size;
	}
	
}
