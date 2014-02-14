package org.apache.camel.component.openshift.producer.domain;

import org.apache.camel.Exchange;
import org.apache.camel.component.openshift.DomainOperationType;
import org.apache.camel.component.openshift.OpenShiftEndpoint;
import org.apache.camel.component.openshift.OpenShiftOperation;
import org.apache.camel.component.openshift.helper.OpenShiftHelper;

import com.openshift.client.IDomain;


/**
 * Performs operations against a given Domain 
 * 
 * @author Andrew Block
 *
 */
public class ModifyDomainOpenShiftOperation implements
		OpenShiftOperation<IDomain> {

	private OpenShiftEndpoint endpoint;
	private String domainId;
	private DomainOperationType operationType;

	public ModifyDomainOpenShiftOperation(OpenShiftEndpoint endpoint,
			String domainId, DomainOperationType operationType) {
		this.endpoint = endpoint;
		this.domainId = domainId;
		this.operationType = operationType;
	}

	@Override
	public IDomain execute(Exchange e) {

		IDomain domain = OpenShiftHelper.getDomainById(
				endpoint.getConfiguration(), domainId, true);

		switch (operationType) {
		case DELETE:
			domain.destroy();
			break;
		default:
			throw new IllegalArgumentException("Invalid Application Operation");
		}

		return OpenShiftHelper.getDomainById(
				endpoint.getConfiguration(), domainId, true);

	}

}
