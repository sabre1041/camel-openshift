package org.apache.camel.component.openshift.consumer.user;

import org.apache.camel.Exchange;
import org.apache.camel.component.openshift.OpenShiftEndpoint;
import org.apache.camel.component.openshift.OpenShiftOperation;

import com.openshift.client.IUser;

/**
 * Returns the User associated with the OpenShift account
 * 
 * @author Andrew Block
 *
 */
public class ViewUserOpenShiftOperation implements OpenShiftOperation<IUser> {

	private OpenShiftEndpoint endpoint;
	
	public ViewUserOpenShiftOperation(OpenShiftEndpoint endpoint) {
		this.endpoint = endpoint;
	}
	
	@Override
	public IUser execute(Exchange e) {
		return endpoint.getConfiguration().getOpenShift().getUser();
	}


}
