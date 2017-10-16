package com.ticketwala.model.test;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ticketwala.model.MovieShow;
import com.ticketwala.model.Order;
import com.ticketwala.model.Seat;

public class OrderTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	private Order createOrder() {
		MovieShow movieShow = new MovieShow("456", "Star Wars III", LocalDateTime.now(), 90);
		return new Order("123", movieShow);
	}
	
	@Test
	public void testAddSeat() {
		Order order = createOrder();
		
		//Add 1 seat to order
		boolean success = order.addSeat(new Seat(1, 1, 30.0));
		
		//Assert on success and only 1 seat exist
		Assert.assertTrue(success && order.getSeats().size() == 1);
		
		//Add same seat (notice its a new object but same seat location - 1,1)
		success = order.addSeat(new Seat(1, 1, 30.0));
		Assert.assertTrue(!success && order.getSeats().size() == 1);

		//Add another seat and verify a success
		success = order.addSeat(new Seat(1, 2, 30.0));
		Assert.assertTrue(success && order.getSeats().size() == 2);
	}

	@Test
	public void testGetTotalCost() {
		Order order = createOrder();
		
		Assert.assertTrue(order.getTotalCost() == 0);
		
		order.addSeat(new Seat(1, 1, 30.0));
		order.addSeat(new Seat(1, 2, 30.0));
		order.addSeat(new Seat(1, 3, 30.0));
		Assert.assertTrue(order.getTotalCost() == 90);
	}

}
