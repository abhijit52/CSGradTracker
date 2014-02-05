package com.shrike.model.services;

// TODO: Auto-generated Javadoc
/**
 * Wrapper around SQL related exceptions.
 * 
 */

//TODO: change from unchecked to checked type exception, - this is just for the 
//      refactoring period.
public class DataAccessException extends Exception  {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The message. @uml.property  name="message" */
	String message = "";
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 * @uml.property  name="message"
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Instantiates a new data access exception.
	 *
	 * @param message the message
	 */
	public DataAccessException(String message) {
		super();
		this.message = message;
	}
}
