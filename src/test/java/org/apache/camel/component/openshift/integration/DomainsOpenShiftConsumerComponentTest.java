package org.apache.camel.component.openshift.integration;

import java.util.List;

import org.apache.camel.component.openshift.BaseOpenShiftConsumerComponentTest;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import com.openshift.client.IDomain;

public class DomainsOpenShiftConsumerComponentTest extends BaseOpenShiftConsumerComponentTest {

	@Test
	@SuppressWarnings("unchecked")
	public void testDomainsComponent() throws InterruptedException {
    	resultEndpoint.expectedMinimumMessageCount(1);
    	resultEndpoint.assertIsSatisfied();
        
        assertThat((List<Object>)resultEndpoint.getExchanges().get(0).getIn().getBody(), CoreMatchers.hasItem(CoreMatchers.instanceOf(IDomain.class)));
    	

	}
	
	@Override
	public String getOpenshiftConsumerCamelComponent() {
		return "openshift://domains?"+OPENSHIFT_CREDENTIALS_OPTIONS;
	}

}
