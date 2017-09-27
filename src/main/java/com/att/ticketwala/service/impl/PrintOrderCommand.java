package com.att.ticketwala.service.impl;

import java.util.List;

import com.att.ticketwala.service.api.Command;
import com.att.ticketwala.service.api.Order;
import com.att.ticketwala.service.api.Result;
import com.att.ticketwala.service.api.Seat;
import com.att.ticketwala.service.api.TicketWalaService;

public class PrintOrderCommand extends Command {

	public PrintOrderCommand(TicketWalaService service, List<String> args) {
		super(service, args);
	}

	@Override
	public Result execute() {
		Result res = null;
		String orderId = this.args.get(0);
		Order order = this.ticketService.getOrder(orderId);
		
		if (order == null) {
			res = new Result(false, "Order " + orderId + "not found");
		} else {			
			StringBuilder sb = new StringBuilder();
			List<Seat> seats = order.getSeats();
			double totalCost = 0;
			sb.append("Order " + orderId + " Summary:\n");
			sb.append("===========================================\n");
			for (Seat seat : seats) {
				sb.append(seat.toString2()).append('\n');
				totalCost += seat.getPrice();
			}
			sb.append("Total: " + totalCost + " NIS\n");
			res = new Result(true, sb.toString());
		}
		return res;
	}

}
