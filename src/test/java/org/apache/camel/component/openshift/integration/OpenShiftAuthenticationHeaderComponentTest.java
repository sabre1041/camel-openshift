package org.apache.camel.component.openshift.integration;

import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.component.openshift.BaseOpenShiftTest;
import org.apache.camel.component.openshift.OpenShiftConstants;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import com.openshift.client.IUser;

public class OpenShiftAuthenticationHeaderComponentTest extends BaseOpenShiftTest {

	@EndpointInject(uri = "mock:result")
	protected MockEndpoint resultEndpoint;

	
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
            	from("direct:start")
            	.setHeader(OpenShiftConstants.USERNAME, constant(USERNAME))
            	.setHeader(OpenShiftConstants.PASSWORD, constant(PASSWORD))
            	.to("openshift://user")
                 .to("mock:result");
            }
        };
    }
	
	@Test
	public void testUserComponent() throws InterruptedException {
		template.sendBody("direct:start", "");
		
		resultEndpoint.expectedMinimumMessageCount(1);
    	resultEndpoint.assertIsSatisfied();
        
        assertThat(resultEndpoint.getExchanges().get(0).getIn().getBody(), CoreMatchers.instanceOf(IUser.class));
    	

	}
	

}
