package com.ticketwala.command.input;

public class AddSeatCommandInput {
	private String orderId;
	private int row;
	private int seatNumber;

	public AddSeatCommandInput(String orderId, int row, int seatNumber) {
		this.orderId = orderId;
		this.row = row;
		this.seatNumber = seatNumber;
	}

	public String getOrderId() {
		return orderId;
	}

	public int getRow() {
		return row;
	}

	public int getSeatNumber() {
		return seatNumber;
	}
}
