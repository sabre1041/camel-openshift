package org.apache.camel.component.openshift.integration;

import org.apache.camel.component.openshift.BaseOpenShiftConsumerComponentTest;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import com.openshift.client.IUser;

public class UserOpenShiftConsumerComponentTest extends BaseOpenShiftConsumerComponentTest {

	@Test
	public void testUserComponent() throws InterruptedException {
    	resultEndpoint.expectedMinimumMessageCount(1);
    	resultEndpoint.assertIsSatisfied();
        
        assertThat(resultEndpoint.getExchanges().get(0).getIn().getBody(), CoreMatchers.instanceOf(IUser.class));
    	

	}
	
	@Override
	public String getOpenshiftConsumerCamelComponent() {
		return "openshift://user?"+OPENSHIFT_CREDENTIALS_OPTIONS;
	}

}
