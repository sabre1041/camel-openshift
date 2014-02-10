package org.apache.camel.component.openshift.integration;

import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.component.openshift.BaseOpenShiftTest;
import org.junit.Test;

public class DeleteApplicationOpenShiftProducerComponentTest  extends BaseOpenShiftTest {

	@EndpointInject(uri = "mock:result")
	protected MockEndpoint resultEndpoint;

	
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
            	from("direct:start")
            	.to("openshift://application/52f2f3a0e0b8cd6988000046/delete?"+OPENSHIFT_CREDENTIALS_OPTIONS)
                 .to("mock:result");
            }
        };
    }
	
	@Test
	public void testModifyApplicationComponent() throws InterruptedException {
		template.sendBody("direct:start", "");
		
		resultEndpoint.expectedMinimumMessageCount(1);
    	resultEndpoint.assertIsSatisfied();
        
        assertEquals("Success", resultEndpoint.getExchanges().get(0).getIn().getBody());
    
	}
	

}
