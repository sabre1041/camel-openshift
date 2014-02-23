package org.apache.camel.component.openshift;


/**
 * Exception for when an unexpected Application operation is invoked
 * 
 * @author Andrew Block
 *
 */
public class InvalidOpenShiftApplicationOperationException extends IllegalArgumentException {
	
	private static final long serialVersionUID = -5921734742746038596L;
	
	private static final String DEFAULT_MESSAGE = "Invalid application operation";
	
	public InvalidOpenShiftApplicationOperationException() {
		super(DEFAULT_MESSAGE);
	}
	
	public InvalidOpenShiftApplicationOperationException(String message) {
		super(message);
	}

}
