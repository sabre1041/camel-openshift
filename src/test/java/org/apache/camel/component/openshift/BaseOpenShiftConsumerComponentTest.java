package org.apache.camel.component.openshift;

import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;

public abstract class BaseOpenShiftConsumerComponentTest extends BaseOpenShiftTest {
	
	@EndpointInject(uri = "mock:result")
	protected MockEndpoint resultEndpoint;
	
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                from(getOpenshiftConsumerCamelComponent())
                  .to("mock:result");
            }
        };
    }
    
	public abstract String getOpenshiftConsumerCamelComponent();

}
