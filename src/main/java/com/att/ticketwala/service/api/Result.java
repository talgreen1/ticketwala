package com.att.ticketwala.service.api;

public class Result {
	private boolean success;
	private String message;
	
	public Result(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public boolean isSuccess() {
		return success;
	}

	@Override
	public String toString() {
		return "Result [success=" + success + ", message=" + message + "]";
	}


}
