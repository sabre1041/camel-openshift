package org.apache.camel.component.openshift.consumer.application;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.component.openshift.OpenShiftEndpoint;
import org.apache.camel.component.openshift.OpenShiftOperation;
import org.apache.camel.component.openshift.helper.OpenShiftHelper;

import com.openshift.client.IApplication;
import com.openshift.client.IDomain;

/**
 * Returns the applications present in a given OpenShift Domain by Domain ID
 * 
 * @author Andrew Block
 *
 */
public class ViewApplicationsOpenShiftOperation implements OpenShiftOperation<List<IApplication>> {

	private OpenShiftEndpoint endpoint;
	private String domainId;
	
	public ViewApplicationsOpenShiftOperation(OpenShiftEndpoint endpoint, String domainId) {
		this.endpoint = endpoint;
		this.domainId = domainId;
	}
	
	@Override
	public List<IApplication> execute(Exchange e) {
		IDomain domain = OpenShiftHelper.getDomainById(endpoint.getConfiguration(), domainId, true);
		return domain.getApplications();
	}

}
