package com.ticketwala.controller.servlet;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TicketWalaController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String hello() {
		return "TicketWala Web Application";
	}
	
}

