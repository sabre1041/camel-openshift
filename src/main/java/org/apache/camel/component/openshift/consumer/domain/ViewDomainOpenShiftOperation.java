package org.apache.camel.component.openshift.consumer.domain;

import org.apache.camel.Exchange;
import org.apache.camel.component.openshift.OpenShiftEndpoint;
import org.apache.camel.component.openshift.OpenShiftOperation;
import org.apache.camel.component.openshift.helper.OpenShiftHelper;

import com.openshift.client.IDomain;

/**
 * Returns the Domain for a given identifier
 * 
 * @author Andrew Block
 *
 */
public class ViewDomainOpenShiftOperation implements OpenShiftOperation<IDomain> {

	private OpenShiftEndpoint endpoint;
	private String domainId;
	
	public ViewDomainOpenShiftOperation(OpenShiftEndpoint endpoint, String domainId) {
		this.endpoint = endpoint;
		this.domainId = domainId;
	}
	
	@Override
	public IDomain execute(Exchange e) {
		return OpenShiftHelper.getDomainById(endpoint.getConfiguration(), domainId, true);
	}

}
