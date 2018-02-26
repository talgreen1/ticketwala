package com.ticketwala.controller.test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.ticketwala.TicketWalaWebApplication;
import com.ticketwala.dao.api.DataAccessService;
import com.ticketwala.dao.impl.DataAccessServiceImpl;
import com.ticketwala.model.MovieShow;

@RunWith(SpringRunner.class)
@SpringBootTest(
		  webEnvironment = WebEnvironment.DEFINED_PORT,
		  classes = TicketWalaWebApplication.class)
public class TicketWalaControllerTest {

	private void prepareDatabase() {
		DataAccessService das = new DataAccessServiceImpl();
		das.deleteAllMovieShows();
		das.createMovieShow(new MovieShow("12345", "Star Wars III", LocalDateTime.now(), 120));
		das.createMovieShow(new MovieShow("12346", "Star Wars V", LocalDateTime.now(), 120));
	}
	
	@Test
	public void testGetMovieShow(){
		prepareDatabase();
		get("/movie_show/12345").
		then().
		body("id", equalTo("12345")).and().body("movieName", equalTo("Star Wars III"));
	}
	
	@Test
	public void testGetMovieShows(){
		prepareDatabase();
		get("/movie_shows").
		then().body("id", hasItems("12346", "12345"));
	}
	
	@Test
	public void testPostMovieShow() {
		prepareDatabase();
		given().
		contentType("application/json").
		body("{	\"name\" : \"Superman\", \"duration\" : 87, \"time\" : \"2017-12-31T18:09\"}").
		when().post("/movie_show").then().assertThat().statusCode(201);
	}
	
	public static void main(String[] args) {
		LocalDateTime ldt = LocalDateTime.parse("2017-10-20T02:23:49.592");
		System.out.println(ldt);
	}
}
