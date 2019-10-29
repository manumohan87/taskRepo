package org.task.services.exception;

/**
 * Class which represents the error response.
 * @author Manu Mohan
 *
 */
public class ErrorResponse {

	private String status;
	private String message;
	private String description;

	/**
	 * Instantiates error response with the error details
	 * @param status status of the error 
	 * @param message the exception message
	 * @param description the request description
	 */
	public ErrorResponse(String status, String message, String description) {
		this.status = status;
		this.message = message;
		this.description = description;
	}

	/**
	 * Gets the status
	 * @return the status of error
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status of error
	 * @param status error status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the exception message.
	 * @return the exception message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the exception message
	 * @param message the exception message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the request description 
	 * @return the request description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the request description
	 * @param description description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
