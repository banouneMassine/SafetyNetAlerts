package com.safetyNet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonIntrovableExeption extends Exception {
	
	public PersonIntrovableExeption(String messageDexception)
	{
		super(messageDexception);
	}

}
