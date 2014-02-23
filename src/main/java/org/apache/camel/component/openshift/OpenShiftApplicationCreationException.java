package org.apache.camel.component.openshift;

import org.apache.camel.RuntimeCamelException;

/**
 * Exception for when an error occurs creating a new OpenShift Application
 * 
 * @author Andrew Block
 *
 */
public class OpenShiftApplicationCreationException extends RuntimeCamelException {
	
	private static final long serialVersionUID = 1607673291899051470L;

	private static final String DEFAULT_MESSAGE = "An error occurred creating the Application";
	
	public OpenShiftApplicationCreationException() {
		super(DEFAULT_MESSAGE);
	}
	
	public OpenShiftApplicationCreationException(String message) {
		super(message);
	}

}
