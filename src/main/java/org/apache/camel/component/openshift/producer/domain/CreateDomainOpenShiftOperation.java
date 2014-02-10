package org.apache.camel.component.openshift.producer.domain;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.RuntimeCamelException;
import org.apache.camel.component.openshift.OpenShiftConstants;
import org.apache.camel.component.openshift.OpenShiftEndpoint;
import org.apache.camel.component.openshift.OpenShiftOperation;

import com.openshift.client.IDomain;

/**
 * Creates an Domain for a User
 * 
 * @author Andrew Block
 *
 */
public class CreateDomainOpenShiftOperation implements OpenShiftOperation<IDomain> {

private OpenShiftEndpoint endpoint;
	
	public CreateDomainOpenShiftOperation(OpenShiftEndpoint endpoint) {
		this.endpoint = endpoint;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public IDomain execute(Exchange e) {
				
		Message message = e.getIn();
		
		String domainId = null;
		
		if(message.getBody() instanceof Map) {
			
			Map<String,String> createApplicationMap = (Map<String, String>) message.getBody();
			
			domainId = createApplicationMap.get(OpenShiftConstants.NEW_DOMAIN_ID);
			
		}
		else if(message.getBody() instanceof String) {
			domainId = (String) message.getBody();
		}
		
		if(domainId == null) {
			throw new RuntimeCamelException("Application and Cartridge Must be Provided");
		}
			
		return endpoint.getConfiguration().getOpenShift().getUser().createDomain(domainId);
		
	}


}
