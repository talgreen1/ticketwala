package com.att.ticketwala.service.impl;

import java.util.List;

import com.att.ticketwala.service.api.Command;
import com.att.ticketwala.service.api.Order;
import com.att.ticketwala.service.api.Result;
import com.att.ticketwala.service.api.Seat;
import com.att.ticketwala.service.api.MovieShow;
import com.att.ticketwala.service.api.TicketWalaService;

public class AddSeatCommand extends Command {

	public AddSeatCommand(TicketWalaService service, List<String> args) {
		super(service, args);
	}
	
	@Override
	public Result execute() {
		String orderId = this.args.get(0);
		int row = Integer.parseInt(this.args.get(1)) - 1;
		int seat = Integer.parseInt(this.args.get(2)) - 1;
		
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
