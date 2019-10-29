package org.task.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Class which handles the exception thrown by the controllers in the Application 
 * @author Manu Mohan
 *
 */
@ControllerAdvice
public class ApplicationExceptionHandler {
	
	
	/**
	 * Error response for all other exceptions
	 * @param ex exception occurred
	 * @param request the client request
	 * @return {@link ErrorResponse} returns the error response
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> globleExcpetionHandler(Exception ex, WebRequest request) {
		ErrorResponse errorDetails = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString() ,ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorResponse>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
