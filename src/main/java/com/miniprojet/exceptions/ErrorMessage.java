package com.miniprojet.exceptions;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorMessage {

	private Integer statusCode;
	private Date timestamp;
	private String message;
	private String path;

	public ErrorMessage(Integer statusCode, Date timestamp, String message, String path) {
		this.statusCode = statusCode;
		this.timestamp = timestamp;
		this.message = message;
		this.path = path;
	}

}
