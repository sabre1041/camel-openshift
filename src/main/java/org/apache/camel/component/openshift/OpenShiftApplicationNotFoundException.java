package org.apache.camel.component.openshift;

import org.apache.camel.RuntimeCamelException;

/**
 * Exception for when an Application is not found
 * 
 * @author Andrew Block
 *
 */
public class OpenShiftApplicationNotFoundException extends RuntimeCamelException {
	
	private static final long serialVersionUID = -1661554503829435930L;

	private static final String DEFAULT_MESSAGE = "Application does not exist";
	
	public OpenShiftApplicationNotFoundException() {
		super(DEFAULT_MESSAGE);
	}
	
	public OpenShiftApplicationNotFoundException(String message) {
		super(message);
	}

}
