package org.apache.camel.component.openshift.consumer.domain;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.component.openshift.OpenShiftEndpoint;
import org.apache.camel.component.openshift.OpenShiftOperation;

import com.openshift.client.IDomain;

/**
 * Returns the Domains included in an OpenShift account
 * 
 * @author Andrew Block
 *
 */
public class ViewDomainsOpenShiftOperation implements OpenShiftOperation<List<IDomain>>  {

	private OpenShiftEndpoint endpoint;
	
	public ViewDomainsOpenShiftOperation(OpenShiftEndpoint endpoint) {
		this.endpoint = endpoint;
	}
	
	@Override
	public List<IDomain> execute(Exchange e) {
		return endpoint.getConfiguration().getOpenShift().getDomains();
	}

}
