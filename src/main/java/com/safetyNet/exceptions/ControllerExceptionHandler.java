package com.safetyNet.exceptions;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

	 //Person
	 @ExceptionHandler(PersonIntrovableExeption.class)
	  public ResponseEntity<ErrorMessage> handlePersonNotFoundException(PersonIntrovableExeption ex, WebRequest request) {
	    ErrorMessage message = new ErrorMessage(
	        HttpStatus.NOT_FOUND.value(),
	        ex.getMessage(),
	        request.getDescription(false));
	    
	    return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
	  }
	 
	 //MedicalRecord
	 @ExceptionHandler(MedicalRecordsIntrouvableException.class)
	  public ResponseEntity<ErrorMessage> handleMedicalRecordNotFoundException(MedicalRecordsIntrouvableException ex, WebRequest request) {
	    ErrorMessage message = new ErrorMessage(
	        HttpStatus.NOT_FOUND.value(),
	        ex.getMessage(),
	        request.getDescription(false));
	    
	    return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
	  }
	  
	 
	 //fireStation
	  @ExceptionHandler(FireStationIntrouvableException.class)
	  public ResponseEntity<ErrorMessage> handleFireStationsNotFoundException(FireStationIntrouvableException ex, WebRequest request) {
	    ErrorMessage message = new ErrorMessage(
	        HttpStatus.NOT_FOUND.value(),
	        ex.getMessage(),
	        request.getDescription(false));
	    
	    return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
	  }
}
