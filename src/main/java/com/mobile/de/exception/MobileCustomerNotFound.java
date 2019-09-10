package com.mobile.de.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MobileCustomerNotFound extends RuntimeException {

	public MobileCustomerNotFound(String message) {
		super(message);
	}

}
