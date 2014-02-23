package org.apache.camel.component.openshift;


/**
 * Exception for when an unexpected Domain operation is invoked
 * 
 * @author Andrew Block
 *
 */
public class InvalidOpenShiftDomainOperationException extends IllegalArgumentException {
	
	private static final long serialVersionUID = -5163472418011527585L;

	private static final String DEFAULT_MESSAGE = "Invalid domain operation";
	
	public InvalidOpenShiftDomainOperationException() {
		super(DEFAULT_MESSAGE);
	}
	
	public InvalidOpenShiftDomainOperationException(String message) {
		super(message);
	}

}
