package com.safetyNet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FireStationIntrouvableException extends Exception {

	public FireStationIntrouvableException(String messageException)
	{
		super(messageException);
	}
}
