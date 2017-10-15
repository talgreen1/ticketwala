package com.ticketwala.command.impl;

import java.util.List;

import com.ticketwala.command.api.Command;
import com.ticketwala.command.api.Result;
import com.ticketwala.model.Order;
import com.ticketwala.model.Seat;
import com.ticketwala.service.api.TicketWalaService;

public class PrintOrderStatusCommand extends Command {

	public PrintOrderStatusCommand(Object commandInput, TicketWalaService tws) {
		super(commandInput, tws);
	}

	@Override
	public Result execute() {
		Result res = null;
		String orderId = (String) this.commandInput;
		Order order = this.ticketWalaService.getOrder(orderId);
		
		if (order == null) {
			res = new Result(false, "Order " + orderId + "not found");
		} else {			
			StringBuilder sb = new StringBuilder();
			List<Seat> seats = order.getSeats();
			sb.append("Order " + orderId + " Summary:\n");
			sb.append("===========================================\n");
			for (Seat seat : seats) {
				sb.append(seat.toString()).append('\n');
			}
			sb.append("Total: " + order.getTotalCost() + " NIS\n");
			res = new Result(true, sb.toString());
		}
		return res;		
	}

}
