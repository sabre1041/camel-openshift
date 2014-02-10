package org.apache.camel.component.openshift.integration;

import java.util.List;

import org.apache.camel.component.openshift.BaseOpenShiftConsumerComponentTest;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import com.openshift.client.IApplication;

public class ApplicationsOpenShiftConsumerComponentTest extends BaseOpenShiftConsumerComponentTest {

	
	@Test
	@SuppressWarnings("unchecked")
	public void testApplicationsComponent() throws InterruptedException {
    	resultEndpoint.expectedMinimumMessageCount(1);
    	resultEndpoint.assertIsSatisfied();
        
        assertThat((List<Object>)resultEndpoint.getExchanges().get(0).getIn().getBody(), CoreMatchers.hasItem(CoreMatchers.instanceOf(IApplication.class)));
    	

	}
	
	@Override
	public String getOpenshiftConsumerCamelComponent() {
		return "openshift://applications/openshiftandroid?"+OPENSHIFT_CREDENTIALS_OPTIONS;
	}

}
