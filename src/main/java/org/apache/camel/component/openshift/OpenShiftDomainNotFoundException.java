package org.apache.camel.component.openshift;

import org.apache.camel.RuntimeCamelException;

/**
 * Exception for when a Domain is not found
 * 
 * @author Andrew Block
 *
 */
public class OpenShiftDomainNotFoundException extends RuntimeCamelException {
	
	private static final long serialVersionUID = 5636168949795357789L;

	private static final String DEFAULT_MESSAGE = "Domain does not exist";
	
	public OpenShiftDomainNotFoundException() {
		super(DEFAULT_MESSAGE);
	}
	
	public OpenShiftDomainNotFoundException(String message) {
		super(message);
	}

}
