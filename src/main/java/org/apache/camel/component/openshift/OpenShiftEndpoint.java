package org.apache.camel.component.openshift;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.api.management.ManagedResource;
import org.apache.camel.component.openshift.consumer.OpenShiftConsumer;
import org.apache.camel.component.openshift.producer.OpenShiftProducer;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.UriEndpoint;

/**
 * Represents a OpenShift Component Endpoint.
 * 
 * @author Andrew Block
 * 
 */
@ManagedResource(description = "OpenShift Camel Endpoint")
@UriEndpoint(scheme="openshift")
public class OpenShiftEndpoint extends DefaultEndpoint {
	
	private OpenShiftConfiguration configuration;


    public OpenShiftEndpoint(String uri, OpenShiftComponent component, OpenShiftConfiguration configuration) {
        super(uri, component);
        
        this.configuration = configuration;
    }

    public Producer createProducer() throws Exception {
    	
    	OpenShiftOperation<?> operation = OpenShiftFactory.getProducerOperation(this, getEndpointUri());
    	
    	OpenShiftProducer producer = new OpenShiftProducer(this, operation);
    	return producer;
    }

    public Consumer createConsumer(Processor processor) throws Exception {
        
    	OpenShiftOperation<?> operation = OpenShiftFactory.getConsumerOperation(this, getEndpointUri());
    	OpenShiftConsumer answer = new OpenShiftConsumer(this, processor, operation);
    	
    	configureConsumer(answer);
    	
    	return answer;
    }
    
    public OpenShiftConfiguration getConfiguration() {
    	return configuration;
    }

    public boolean isSingleton() {
        return true;
    }
}
