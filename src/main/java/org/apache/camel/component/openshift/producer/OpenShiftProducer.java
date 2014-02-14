package org.apache.camel.component.openshift.producer;

import org.apache.camel.Exchange;
import org.apache.camel.Producer;
import org.apache.camel.component.openshift.OpenShiftEndpoint;
import org.apache.camel.component.openshift.OpenShiftOperation;
import org.apache.camel.component.openshift.helper.OpenShiftHelper;
import org.apache.camel.impl.DefaultProducer;

/**
 * {@link Producer} for the OpenShift Component
 * 
 * @author Andrew Block
 *
 */
public class OpenShiftProducer extends DefaultProducer {

	private OpenShiftOperation<?> operation;
	private OpenShiftEndpoint endpoint;
	
	public OpenShiftProducer(OpenShiftEndpoint endpoint, OpenShiftOperation<?> operation) {
		super(endpoint);
		this.operation = operation;
		this.endpoint = endpoint;
		
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		
		// Parse Authentication Headers
		OpenShiftHelper.parseAuthenticationHeaders(exchange.getIn(), endpoint.getConfiguration());
		
		// Execute operation
		Object o = operation.execute(exchange);
		exchange.getOut().setBody(o);
		
		// Copy Headers from Input Message to Output Message
		for(String key : exchange.getIn().getHeaders().keySet()) {
			Object value = exchange.getIn().getHeader(key);
			exchange.getOut().setHeader(key, value);
		}
			
			
	}

}
