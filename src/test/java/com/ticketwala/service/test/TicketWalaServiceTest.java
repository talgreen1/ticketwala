package com.ticketwala.service.test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ticketwala.command.api.Result;
import com.ticketwala.dao.api.DataAccessService;
import com.ticketwala.dao.impl.DataAccessServiceImpl;
import com.ticketwala.model.MovieShow;
import com.ticketwala.model.Order;
import com.ticketwala.service.api.TicketWalaService;
import com.ticketwala.service.impl.TicketWalaServiceImpl;

public class TicketWalaServiceTest {

	private TicketWalaService tws = new TicketWalaServiceImpl();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllMovies() {
		prepareDatabase();
		
		List<MovieShow> movieShows = this.tws.getMovieShows();
		Assert.assertTrue(movieShows.size() == 2);
	}
	
	@Test
	public void testCreateOrderToNonExistingMovieShow() {
		Result res = this.tws.createOrder("1118822");
		System.out.println(res.getMessage());
		Assert.assertFalse(res.isSuccess());
	}

	@Test
	public void testCreateOrderToExistingMovieShow() {
		prepareDatabase();

		Result res = this.tws.createOrder("12345");

		// We assume order Id is in the message part. We can make the API to return the
		// Order object itself.
		String orderId = res.getMessage();
		
		//Verify Success and also that order is found by TWS
		Assert.assertTrue(res.isSuccess() && this.tws.getOrder(orderId).getId().equals(orderId));
	}

	@Test
	public void testAddSeatTicketToOrder() {
		prepareDatabase();

		Result res = this.tws.createOrder("12345");

		// Check order is created
		// We assume order Id is in the message part. We can make the API to return the
		// Order object itself.
		String orderId = res.getMessage();
		Order order = this.tws.getOrder(orderId);
		Assert.assertNotNull(order);

		Result addSeatResult = this.tws.addSeatTicket(orderId, 1, 1);

		System.out.println(addSeatResult.getMessage());

		Assert.assertTrue(addSeatResult.isSuccess());

		// Test that adding the same seat to order fails
		addSeatResult = this.tws.addSeatTicket(orderId, 1, 1);
		Assert.assertFalse(addSeatResult.isSuccess());

		System.out.println(addSeatResult.getMessage());

	}
	
	@Test
	public void testSubmitOrder() {
		prepareDatabase();
				
		//Make Order
		Result res = this.tws.createOrder("12345");
		
		// Check order is created
		// We assume order Id is in the message part. We can make the API to return the
		// Order object itself.
		String orderId = res.getMessage();
		Order order = this.tws.getOrder(orderId);
		Assert.assertNotNull(order);
		
		//Add Seat to Order and Test
		Result addSeatResult = this.tws.addSeatTicket(orderId, 1, 1);
		System.out.println(addSeatResult.getMessage());
		Assert.assertTrue(addSeatResult.isSuccess());

		//Submit and Test
		Result submitOrderResult = tws.submitOrder(orderId);
		Assert.assertTrue(submitOrderResult.isSuccess());
		
		//Make Order Another Order
		res = this.tws.createOrder("12345");

		// Check order is created
		// We assume order Id is in the message part. We can make the API to return the
		// Order object itself.
		orderId = res.getMessage();
		Assert.assertNotNull(order); //Order Created Successfully
		
		//Add Seat to Order and Test - Should Fail because same seat was already ordered.
		addSeatResult = this.tws.addSeatTicket(orderId, 1, 1);
		System.out.println(addSeatResult.getMessage());
		Assert.assertFalse(addSeatResult.isSuccess());

		System.out.println(addSeatResult.getMessage());

	}

	@Test
	public void testGetMovieShow(){
		prepareDatabase();
		Assert.assertTrue(this.tws.getMovieShow("12346").getId().equals("12346"));
	}
	
	@Test
	public void testGetMovieShows(){
		prepareDatabase();
		List<MovieShow> movieShows = this.tws.getMovieShows();
		Assert.assertTrue(movieShows.stream().map((ms) -> ms.getId()).collect(Collectors.toList()).contains("12345"));
	}
	
	@Test
	public void testPostMovieShow() {
		prepareDatabase();
		Assert.assertTrue(this.tws.addMovieShow(new MovieShow("12347", "Chipopo", LocalDateTime.now(), 120)).isSuccess());
	}
	
	private void prepareDatabase() {
		DataAccessService das = new DataAccessServiceImpl();
		das.deleteAllMovieShows();
		das.createMovieShow(new MovieShow("12345", "Star Wars III", LocalDateTime.now(), 120));
		das.createMovieShow(new MovieShow("12346", "Star Wars V", LocalDateTime.now(), 120));
	}
	
}
