package com.ticketwala.model;

public class CinemaHall {
	private Seat[][] seatsArray;
	private double seatPrice;
	
	public CinemaHall(int rows, int seats, double seatPrice) {
		this.seatsArray = new Seat[rows][seats];
		this.seatPrice = seatPrice;
		for (int i = 0; i < seatsArray.length; i++) {
			for (int j = 0; j < seatsArray[i].length; j++) {
				seatsArray[i][j] = new Seat(i, j, this.seatPrice);
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (Seat[] seats : seatsArray) {
			for (Seat seat : seats) {
				sb.append(seat.isSold() ? 'X' : '*');
			}
			sb.append('\n');
		}

		return sb.toString();
	}
	
	public Seat getSeat(int row, int seatNumber){
		return this.seatsArray[row][seatNumber];
	}
}
