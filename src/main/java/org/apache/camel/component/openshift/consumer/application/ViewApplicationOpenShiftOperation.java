package org.apache.camel.component.openshift.consumer.application;

import org.apache.camel.Exchange;
import org.apache.camel.component.openshift.OpenShiftEndpoint;
import org.apache.camel.component.openshift.OpenShiftOperation;
import org.apache.camel.component.openshift.helper.OpenShiftHelper;

import com.openshift.client.IApplication;

/**
 * Returns an application by a given UUID
 * 
 * @author Andrew Block
 *
 */
public class ViewApplicationOpenShiftOperation implements OpenShiftOperation<IApplication> {

	private OpenShiftEndpoint endpoint;
	private String appUUID;
	
	public ViewApplicationOpenShiftOperation(OpenShiftEndpoint endpoint, String appUUID) {
		this.endpoint = endpoint;
		this.appUUID = appUUID;
	}
	
	@Override
	public IApplication execute(Exchange e) {
		return OpenShiftHelper.getApplicationByUUID(endpoint.getConfiguration(), appUUID, true);
	}

}
