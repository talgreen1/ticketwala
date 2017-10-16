package com.ticketwala.command.impl;

import com.ticketwala.command.api.Command;
import com.ticketwala.command.api.Result;
import com.ticketwala.command.input.AddSeatCommandInput;
import com.ticketwala.model.MovieShow;
import com.ticketwala.model.Order;
import com.ticketwala.model.Seat;
import com.ticketwala.service.api.TicketWalaService;

public class AddSeatCommand extends Command {

	public AddSeatCommand(TicketWalaService service, Object input) {
		super(service, input);
	}
	
	@Override
	public Result execute() {
		AddSeatCommandInput input = (AddSeatCommandInput) this.commandInput;
		String orderId = input.getOrderId();
		int row = input.getRow();
		int seat = input.getSeat();
		
		Order order = this.ticketService.getOrder(orderId);
		MovieShow movieShow = order.getMovieShow();
		double seatPrice = order.getMovieShow().getTicketPrice();
		Seat seatToAdd = new Seat(row, seat, seatPrice);
		
		if (movieShow.getCinemaHall().getSeats()[row][seat].isSold()) {
			return new Result(false, "Seat ordered. Cannot add this seat!");
		} else if (order.getSeats().contains(seatToAdd)){	
			return new Result(false, "Seat already exist in your order.");
		} else {
			return this.ticketService.addSeat(orderId, seatToAdd);
		}
	}

}
