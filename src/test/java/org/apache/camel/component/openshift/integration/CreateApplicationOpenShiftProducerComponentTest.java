package org.apache.camel.component.openshift.integration;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.component.openshift.BaseOpenShiftTest;
import org.apache.camel.component.openshift.OpenShiftConstants;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import com.openshift.client.IApplication;
import com.openshift.client.cartridge.StandaloneCartridge;

public class CreateApplicationOpenShiftProducerComponentTest  extends BaseOpenShiftTest {

	@EndpointInject(uri = "mock:result")
	protected MockEndpoint resultEndpoint;

	
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
            	from("direct:start")
            	.to("openshift://domain/openshiftandroid/createapplication?"+OPENSHIFT_CREDENTIALS_OPTIONS)
                 .to("mock:result");
            }
        };
    }
	
	@Test
	public void testCreateApplicationComponent() throws InterruptedException {
		
		String appName = "camelcomponentphp";
		
		Map<String, String> applicationParameters = new HashMap<String,String>();
		applicationParameters.put(OpenShiftConstants.NEW_APPLICATION_NAME, appName);
		applicationParameters.put(OpenShiftConstants.NEW_APPLICATION_CARTRIDGE, StandaloneCartridge.NAME_PHP);
		
		
		template.sendBody("direct:start", applicationParameters);
		resultEndpoint.expectedMinimumMessageCount(1);
    	resultEndpoint.assertIsSatisfied();
        
        assertThat(resultEndpoint.getExchanges().get(0).getIn().getBody(), CoreMatchers.instanceOf(IApplication.class));
    	assertEquals("camelcomponentphp",((IApplication)resultEndpoint.getExchanges().get(0).getIn().getBody()).getName());

	}
	

}
