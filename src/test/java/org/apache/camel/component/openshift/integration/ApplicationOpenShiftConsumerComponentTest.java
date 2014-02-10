package org.apache.camel.component.openshift.integration;

import org.apache.camel.component.openshift.BaseOpenShiftConsumerComponentTest;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import com.openshift.client.IApplication;

public class ApplicationOpenShiftConsumerComponentTest extends BaseOpenShiftConsumerComponentTest {

	
	@Test
	public void testApplicationsComponent() throws InterruptedException {
    	resultEndpoint.expectedMinimumMessageCount(1);
    	resultEndpoint.assertIsSatisfied();
        
        assertThat(resultEndpoint.getExchanges().get(0).getIn().getBody(), CoreMatchers.instanceOf(IApplication.class));
    	

	}
	
	@Override
	public String getOpenshiftConsumerCamelComponent() {
		return "openshift://application/insertuuidhere?"+OPENSHIFT_CREDENTIALS_OPTIONS;
	}

}
