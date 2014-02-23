package org.apache.camel.component.openshift;

import org.apache.camel.RuntimeCamelException;

/**
 * Exception for when an error occurs creating a new OpenShift Application
 * 
 * @author Andrew Block
 *
 */
public class OpenShiftDomainCreationException extends RuntimeCamelException {
	
	private static final long serialVersionUID = 1607673291899051470L;

	private static final String DEFAULT_MESSAGE = "An error occurred creating the Application";
	
	public OpenShiftDomainCreationException() {
		super(DEFAULT_MESSAGE);
	}
	
	public OpenShiftDomainCreationException(String message) {
		super(message);
	}

}
