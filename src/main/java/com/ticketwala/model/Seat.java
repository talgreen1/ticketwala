package com.ticketwala.model;

public class Seat {
	private int row;
	private int seat;
	private boolean sold;
	private double price;

	public Seat(int row, int seat, double price) {
		this.row = row;
		this.seat = seat;
		this.sold = false;
		this.price = price;
	}

	public int getRow() {
		return row;
	}

	public int getSeat() {
		return seat;
	}

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}
	
	@Override
	public String toString() {
		return sold ? "X" : "-";
	}

	public String toString2() {
		return String.format("{ row : %d, seat : %d, price : %f }", row+1, seat+1, price);
	}

	public double getPrice() {
		return price;
	}

	@Override
	public boolean equals(Object obj) {
		Seat otherSeat = (Seat) obj;
		return this.row == otherSeat.row && this.seat == otherSeat.seat;
	}
}
