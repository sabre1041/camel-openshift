package org.apache.camel.component.openshift.consumer;

import org.apache.camel.Consumer;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.openshift.OpenShiftOperation;
import org.apache.camel.impl.DefaultConsumer;

/**
 * {@link Consumer} for the OpenShift Component
 * 
 * @author Andrew Block
 *
 */
public class OpenShiftConsumer extends DefaultConsumer   {

	private OpenShiftOperation<?> operation;
	
	public OpenShiftConsumer(Endpoint endpoint, Processor processor, OpenShiftOperation<?> operation) {
		super(endpoint, processor);

		this.operation = operation;
	}
	
	@Override
	protected void doStart() throws Exception {
		super.doStart();

		Exchange e = getEndpoint().createExchange();
		
		// Execute operation
		Object o = operation.execute(e);
		e.getIn().setBody(o);
		
		getProcessor().process(e);
		
	}

}
